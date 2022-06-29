package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.ListOFgoods;
import com.myisu_1.isu.models.MarvelPromo;
import com.myisu_1.isu.models.Suppliers;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.ListOFgoodsRepositoriy;
import com.myisu_1.isu.repo.MarwelPromoRepositoriy;
import com.myisu_1.isu.repo.SuppliersRepositoriy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
public class loadingController {

    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;

    @Autowired
    private ListOFgoodsRepositoriy listOFgoodsRepositoriy;

    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy ;


    @GetMapping("/loading")
    public String home(Model model) {

        return "loading";
    }

    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {

        List<MarvelPromo> tempStudentList = new ArrayList<MarvelPromo>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(1);
        marwelPromoRepositoriy.deleteAll();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            MarvelPromo tempStudent = new MarvelPromo();

            XSSFRow row = worksheet.getRow(i);

            tempStudent.setId((int) row.getCell(0).getNumericCellValue());
            tempStudent.setPromoCode(row.getCell(1).getStringCellValue());
            tempStudent.setStartPromo(row.getCell(2).getDateCellValue());
            tempStudent.setEndPromo(row.getCell(3).getDateCellValue());
            tempStudent.setArticleNumber(row.getCell(4).getStringCellValue());
            tempStudent.setArticleNumber(row.getCell(4).getStringCellValue());
            tempStudent.setVision((int) row.getCell(5).getNumericCellValue());
            tempStudent.setNewVision((int) row.getCell(6).getNumericCellValue());
            tempStudent.setDiscount((int) row.getCell(7).getNumericCellValue());
            tempStudent.setCompensation((int) row.getCell(8).getNumericCellValue());
            tempStudent.setCollecting(row.getCell(9).getDateCellValue());
            tempStudent.setStatus(row.getCell(10).getStringCellValue());
            tempStudentList.add(tempStudent);


           // System.out.println(row.getCell(0).getNumericCellValue());
        }
        marwelPromoRepositoriy.saveAll(tempStudentList);
        return "loading";
    }
    @GetMapping("/importVVP")
    public String importVVP(@RequestParam("fileVVP") MultipartFile fileVVP) throws IOException {

        List<ListOFgoods> listOFgoods = new ArrayList<ListOFgoods>();
        XSSFWorkbook workbook = new XSSFWorkbook(fileVVP.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(1);
        listOFgoodsRepositoriy.deleteAll();

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            ListOFgoods listOFgoods1 = new ListOFgoods();

            XSSFRow row = worksheet.getRow(i);

            listOFgoods1.setId((int) row.getCell(0).getNumericCellValue());
            listOFgoods1.setModel(row.getCell(1).getStringCellValue());
            listOFgoods1.setPrice((int) row.getCell(2).getNumericCellValue());
            listOFgoods1.setPricePromo((int) row.getCell(3).getNumericCellValue());
            listOFgoods1.setStartPromo( row.getCell(4).getDateCellValue());
            listOFgoods1.setEndPromo( row.getCell(5).getDateCellValue());
            listOFgoods1.setDiscountUE((int) row.getCell(6).getNumericCellValue());


            listOFgoods.add(listOFgoods1);


           //  System.out.println(row.getCell(2).getNumericCellValue());

        }
        listOFgoodsRepositoriy.saveAll(listOFgoods);
        return "loading";
    }

    @PostMapping("/importsuppliers")
    public String importsuppliers(@RequestParam("importsuppliers") MultipartFile importsuppliers,Model model) throws IOException {
        int count = 0;
        List<Suppliers> all_listSuppliers = (List<Suppliers>) suppliersRepositoriy.findAll();
        List<Suppliers> listSuppliers = new ArrayList<Suppliers>();
        XSSFWorkbook workbook = new XSSFWorkbook(importsuppliers.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Suppliers listSuppliers1 = new Suppliers();

            XSSFRow row = worksheet.getRow(i);

            listSuppliers1.setImei(row.getCell(0).getStringCellValue());
            listSuppliers1.setSuppliers(row.getCell(1).getStringCellValue());
            listSuppliers.add(listSuppliers1);

            for (int j = 1; j < all_listSuppliers.size(); j++) {

                if (all_listSuppliers.get(j).getImei().equals(listSuppliers1.getImei())) {
                    count++;
                    suppliersRepositoriy.deleteById(all_listSuppliers.get(j).getId());
                    System.out.println(count);
                }
            }
        }

        suppliersRepositoriy.saveAll(listSuppliers);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
            return "loading";
    }

}
