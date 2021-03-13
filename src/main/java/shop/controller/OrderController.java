package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.entity.Item;
import shop.entity.Order;
import shop.repos.OrderRepo;
import shop.service.ItemService;
import shop.service.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String itemList(Map<String, Object> model) {
        Iterable<Item> items = itemService.loadAllItems();
        model.put("items", items);
        return "itemList";
    }

    @GetMapping("{item}")
    public String userEditForm(@PathVariable Item item, Model model) {
        model.addAttribute("item", item);
        return "orderAdd";
    }

    @GetMapping("list")
    public String orderList(Map<String, Object> model) {
        Iterable<Order> orders = orderService.loadOrderByActive();
        model.put("orders", orders);
        return "orderList";
    }


    @PostMapping("orderAddOrd")
    public String addPhone(@RequestParam String _model, @RequestParam String _description, @RequestParam double _price,
                           @RequestParam int _qty, @RequestParam String _username, @RequestParam String _address, @RequestParam String typePayment,
                           Map<String, Object> model) {
        int _amount = _qty * (int) _price;
        boolean active = true;
        Order order = new Order(_username, _model, _description, _qty, _amount, _address, typePayment, active);
        orderRepo.save(order);
        return "redirect:/item";
    }

    @PostMapping("addSale")
    public String addSale(@RequestParam("orderId") Order order) {
        order.setActive(false);
        orderRepo.save(order);
        return "redirect:/order/list";
    }

    @GetMapping("/o/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerkey = "Content-Disposition";
        String headerValue = "attachement; filename=orders.xls";

        response.setHeader(headerkey, headerValue);

        List<Order> list = (List<Order>) orderService.loadAllOrders();

        OrderExcelExporter excelExporter = new OrderExcelExporter(list);
        excelExporter.export(response);
    }

}
