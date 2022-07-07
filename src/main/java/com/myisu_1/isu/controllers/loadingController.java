package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

@Controller
public class loadingController {

    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;

    @Autowired
    private ListOFgoodsRepositoriy listOFgoodsRepositoriy;

    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy ;

    @Autowired
    private SalesRepositoriy salesRepositoriy ;

    @Autowired
    private ComboRepositoriy comboRepositoriy ;

    @Autowired
    private ValueVUERepositoriy valueVUERepositoriy;

    @Autowired
    private TradeINRepository tradeINRepository;


    @GetMapping("/loading")
    public String home(Model model) {

        return "loading";
    }

    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, Model model) throws IOException {
        long start = System.currentTimeMillis();
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
            tempStudent.setVision((int) row.getCell(5).getNumericCellValue());
            tempStudent.setNewVision((int) row.getCell(6).getNumericCellValue());
            tempStudent.setDiscount((int) row.getCell(7).getNumericCellValue());
            tempStudent.setCompensation((int) row.getCell(8).getNumericCellValue());
            tempStudent.setCollecting(row.getCell(9).getDateCellValue());
            tempStudent.setStatus(row.getCell(10).getStringCellValue());
            tempStudentList.add(tempStudent);


           // System.out.println(row.getCell(0).getNumericCellValue());
        }
        marwelPromoRepositoriy.saveAllAndFlush(tempStudentList);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
       // System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
       // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
        return "loading";
    }
    @GetMapping("/importVVP")
    public String importVVP(@RequestParam("fileVVP") MultipartFile fileVVP, Model model) throws IOException {
        long start = System.currentTimeMillis();
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
        listOFgoodsRepositoriy.saveAllAndFlush(listOFgoods);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
       // System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
       // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
        return "loading";
    }

    @PostMapping("/importsuppliers")
    public String importsuppliers(@RequestParam("importsuppliers") MultipartFile importsuppliers,Model model) throws IOException {
        int count = 0;
        List<Suppliers> all_listSuppliers = (List<Suppliers>) suppliersRepositoriy.findAll();
        List<Suppliers> listSuppliers = new ArrayList<Suppliers>();
        XSSFWorkbook workbook = new XSSFWorkbook(importsuppliers.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
       // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Suppliers listSuppliers1 = new Suppliers();

            XSSFRow row = worksheet.getRow(i);

            listSuppliers1.setImei(row.getCell(0).getStringCellValue());
            listSuppliers1.setSuppliers(row.getCell(1).getStringCellValue());
            listSuppliers.add(listSuppliers1);

            for (int j = 0; j < all_listSuppliers.size(); j++) {

                if (all_listSuppliers.get(j).getImei().equals(listSuppliers1.getImei())) {
                    count++;
                    suppliersRepositoriy.deleteById(all_listSuppliers.get(j).getId());
           //         System.out.println(count);
                }
            }
        }

        suppliersRepositoriy.saveAllAndFlush(listSuppliers);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
       // System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
       // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
            return "loading";
    }
    @PostMapping("/importsales")
    public String importsales(@RequestParam("importsales") MultipartFile importsales,Model model) throws IOException, ParseException {
        int count = 0;
        List<Sales> all_listSales = (List<Sales>) salesRepositoriy.findAll();
        List<Sales> listSales = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importsales.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        System.out.println(((List<Sales>) salesRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Sales listSales1 = new Sales();

            XSSFRow row = worksheet.getRow(i);

            listSales1.setImeis(row.getCell(0).getStringCellValue());
            listSales1.setShop(row.getCell(1).getStringCellValue());
            listSales1.setNomenclature(row.getCell(2).getStringCellValue());
            listSales1.setDateSales(dateString(row.getCell(3).getStringCellValue()));
            listSales.add(listSales1);

            for (int j = 0; j < all_listSales.size(); j++) {

                if (all_listSales.get(j).getImeis().equals(listSales1.getImeis())) {
                    count++;
                    salesRepositoriy.deleteById(all_listSales.get(j).getId());
           //         System.out.println(count);
                }
            }
        }

        salesRepositoriy.saveAll(listSales);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
       // System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
    //    System.out.println(((List<Sales>) salesRepositoriy.findAll()).size());
        return "loading";
    }
    @PostMapping("/importcombo")
    public String importcombo(@RequestParam("importcombo") MultipartFile importcombo,Model model) throws IOException, ParseException {
        int count = 0;
        List<Combo> all_listCombo = (List<Combo>) comboRepositoriy.findAll();
        List<Combo> listCombo = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importcombo.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
    //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Combo listCombo1 = new Combo();

            XSSFRow row = worksheet.getRow(i);
if(row.getCell(20).getStringCellValue().equals("Сотовые телефоны")) {

    listCombo1.setDate(dateStringCombo(row.getCell(14).getStringCellValue()));
    listCombo1.setImei(row.getCell(19).getStringCellValue());
    listCombo1.setCombo(row.getCell(29).getStringCellValue());
    listCombo1.setResume(row.getCell(35).getStringCellValue());
    listCombo1.setReason(row.getCell(36).getStringCellValue());
    listCombo1.setSize(String.valueOf(row.getCell(37).getNumericCellValue()));
    //listCombo1.setSize(row.getCell(3).getStringCellValue());
    listCombo1.setPayment((double) row.getCell(38).getNumericCellValue());
    listCombo.add(listCombo1);
}
            for (int j = 0; j < all_listCombo.size(); j++) {

                if (all_listCombo.get(j).getImei().equals(listCombo1.getImei())) {
                    count++;
                    comboRepositoriy.deleteById(all_listCombo.get(j).getId());
    //                System.out.println(count);
                }
            }
        }

        comboRepositoriy.saveAll(listCombo);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
    //    System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
    //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        return "loading";
    }
    @PostMapping("/importValueVUE")
    public String importValueVUE(@RequestParam("importValueVUE") MultipartFile importValueVUE,Model model) throws IOException, ParseException {
        int count = 0;
        List<ValueVUE> all_listVUE = (List<ValueVUE>) valueVUERepositoriy.findAll();
        List<ValueVUE> listVUE = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importValueVUE.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            ValueVUE listVUE1 = new ValueVUE();

            XSSFRow row = worksheet.getRow(i);

                listVUE1.setNomenclature(row.getCell(0).getStringCellValue());
                listVUE1.setImei(String.valueOf(row.getCell(1).getNumericCellValue()));
                listVUE1.setQuality(row.getCell(2).getStringCellValue());
                listVUE1.setDateOFsale (dateString(row.getCell(3).getStringCellValue()));
                listVUE1.setShop(row.getCell(4).getStringCellValue());
                listVUE1.setValueVUE((int) row.getCell(5).getNumericCellValue());
                listVUE.add(listVUE1);
                for (int j = 0; j < all_listVUE.size(); j++) {

                if (all_listVUE.get(j).getImei().equals(listVUE1.getImei())) {
                    count++;
                    valueVUERepositoriy.deleteById(all_listVUE.get(j).getId());
                       //            System.out.println(count);
                }
            }
        }

        valueVUERepositoriy.saveAll(listVUE);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        //    System.out.println(df.format(new Date(timeWorkCode)));
        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        return "loading";
    }

    @PostMapping("/importTradeIN")
    public String importTradeIN(@RequestParam("importTradeIN") MultipartFile importTradeIN,Model model) throws IOException, ParseException {
        int count = 0;
        List<TradeIN> all_listTradeIN = (List<TradeIN>) tradeINRepository.findAll();
        List<TradeIN> listTradeIN = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importTradeIN.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        long start = System.currentTimeMillis();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            TradeIN listTradeIN1 = new TradeIN();

            XSSFRow row = worksheet.getRow(i);

            listTradeIN1.setReceiptDate(dateString(row.getCell(0).getStringCellValue()));
            listTradeIN1.setNomenclature(row.getCell(1).getStringCellValue());
            listTradeIN1.setIMEI(String.valueOf(row.getCell(2).getNumericCellValue()));
            listTradeIN1.setProductPrice((int) row.getCell(3).getNumericCellValue());
            listTradeIN1.setDiscount((int) row.getCell(4).getNumericCellValue());
            listTradeIN1.setAmount((int) row.getCell(5).getNumericCellValue());

            listTradeIN.add(listTradeIN1);
            for (int j = 0; j < all_listTradeIN.size(); j++) {

                if (listTradeIN.get(j).getIMEI().equals(listTradeIN1.getIMEI())) {
                    count++;
                    tradeINRepository.deleteById(listTradeIN.get(j).getId());
                    //            System.out.println(count);
                }
            }
        }

        tradeINRepository.saveAll(listTradeIN);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        //    System.out.println(df.format(new Date(timeWorkCode)));
        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        //    System.out.println(((List<Combo>) comboRepositoriy.findAll()).size());
        return "loading";
    }
    private Date dateString(String stringCellValue) throws ParseException {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.ENGLISH);
        date = formatter.parse(stringCellValue);
        return date;
    }
    private Date dateStringCombo(String stringCellValue) throws ParseException {
        Date date = null;
        SimpleDateFormat formatter;
        if(stringCellValue.replace("T"," ").length()==19) {
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        }else{
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.ENGLISH);
        }
        date = formatter.parse(stringCellValue.replace("T", " "));
        return date ;

    }
 }
