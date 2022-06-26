package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.ListOFgoods;
import com.myisu_1.isu.models.MarvelPromo;
import com.myisu_1.isu.repo.ListOFgoodsRepositoriy;
import com.myisu_1.isu.repo.MarwelPromoRepositoriy;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class loadingController {

    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;

    @Autowired
    private ListOFgoodsRepositoriy listOFgoodsRepositoriy;


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
    @PostMapping("/importVVP")
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

}
