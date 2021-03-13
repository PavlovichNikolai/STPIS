package shop.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import shop.entity.Order;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Order> orders;

    public OrderExcelExporter(List<Order> orders) {
        this.orders = orders;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Orders");
    }

    private void writeHeaderRow(){
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Order ID");
        Cell cell1 = row.createCell(1);
        cell1.setCellValue("Username");
        Cell cell2 = row.createCell(2);
        cell2.setCellValue("Model");
        Cell cell3 = row.createCell(3);
        cell3.setCellValue("Description");
        Cell cell4 = row.createCell(4);
        cell4.setCellValue("qty");
        Cell cell5 = row.createCell(5);
        cell5.setCellValue("Amount");
        Cell cell6 = row.createCell(6);
        cell6.setCellValue("Address");
        Cell cell7 = row.createCell(7);
        cell7.setCellValue("Payment");
    }

    private void writeDataRows(){
        int rowsCount = 1;
        for (Order order : orders){
            Row row = sheet.createRow(rowsCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(order.getIdorder());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(order.get_username());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(order.get_model());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(order.get_description());
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(order.get_qty());
            Cell cell5 = row.createCell(5);
            cell5.setCellValue(order.get_amount());
            Cell cell6 = row.createCell(6);
            cell6.setCellValue(order.get_address());
            Cell cell7 = row.createCell(7);
            cell7.setCellValue(order.getPayment());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }


}
