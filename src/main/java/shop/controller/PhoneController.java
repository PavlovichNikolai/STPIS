package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.entity.Item;
import shop.service.PhoneService;

import java.util.Map;

@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping
    public String phoneList(Map<String, Object> model){
        Iterable<Item> items = phoneService.loadAllPhone();
        model.put("items", items);
        return "phone";
    }

    @PostMapping("deletePhone")
    public String deletePhone(@RequestParam("phoneId") Item item, Map<String, Object> model){
        phoneService.deleteItems(item);
        Iterable<Item> items = phoneService.loadAllPhone();
        model.put("items", items);

        return "phone";
    }
}
