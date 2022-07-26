package com.myisu_1.isu.controllers;


import com.myisu_1.isu.models.*;
import com.myisu_1.isu.repo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import java.text.DecimalFormat;
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

    @Autowired
    private PromoRepositoriy promoRepositoriy;

    @Autowired
    private PhoneRepositoriy phoneRepositoriy;

    @Autowired
    private PostRepositoriy authorizationTt;

    @Autowired
    private RemainingPhonesRepository remainingPhonesRepository;




    @GetMapping("/loading")
    public String home(Model model) {

        return "loading";
    }

    @PostMapping("/import")
    public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile, Model model) throws IOException, ParseException {
        long start = System.currentTimeMillis();
        List<MarvelPromo> tempStudentList = new ArrayList<MarvelPromo>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        marwelPromoRepositoriy.deleteAll();
        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            MarvelPromo tempStudent = new MarvelPromo();

            XSSFRow row = worksheet.getRow(i);
if(row.getCell(4).getStringCellValue().equals("Федерально")){
    // tempStudent.setId((int) row.getCell(0).getNumericCellValue());
    tempStudent.setPromoCode(row.getCell(0).getStringCellValue());
    tempStudent.setStartPromo(dateDate(row.getCell(6).getStringCellValue()));
    tempStudent.setEndPromo(dateDate(row.getCell(7).getStringCellValue()));
    tempStudent.setArticleNumber(row.getCell(10).getStringCellValue());
    tempStudent.setVision((int) row.getCell(11).getNumericCellValue());
    tempStudent.setNewVision((int) row.getCell(12).getNumericCellValue());
    tempStudent.setDiscount((int) row.getCell(13).getNumericCellValue());
    tempStudent.setCompensation((int) row.getCell(14).getNumericCellValue());
    tempStudent.setCollecting(dateDate(row.getCell(16).getStringCellValue()));
    tempStudent.setStatus(row.getCell(23).getStringCellValue());
    tempStudentList.add(tempStudent);
}



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
    @PostMapping("/importVVP")
    public String importVVP(@RequestParam("fileVVP") MultipartFile fileVVP, Model model) throws IOException {
        long start = System.currentTimeMillis();
        List<ListOFgoods> listOFgoods = new ArrayList<ListOFgoods>();
        XSSFWorkbook workbook = new XSSFWorkbook(fileVVP.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        listOFgoodsRepositoriy.deleteAll();

        for(int i=8;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            ListOFgoods listOFgoods1 = new ListOFgoods();

            XSSFRow row = worksheet.getRow(i);


            listOFgoods1.setName(row.getCell(2).getStringCellValue());
            listOFgoods1.setBrend(row.getCell(4).getStringCellValue());
            listOFgoods1.setModel(row.getCell(5).getStringCellValue());
            if(row.getCell(6).getCellType() == CellType.STRING) {
                listOFgoods1.setMatrix(row.getCell(6).getStringCellValue());
            }else{
                listOFgoods1.setMatrix(String.valueOf(row.getCell(6).getNumericCellValue()));
            }
            listOFgoods1.setPriceZak((int) row.getCell(8).getNumericCellValue());
            listOFgoods1.setPrice((int) row.getCell(11).getNumericCellValue());
            listOFgoods1.setPricePromo((int) row.getCell(12).getNumericCellValue());
            listOFgoods1.setStartPromo( row.getCell(13).getDateCellValue());
            listOFgoods1.setEndPromo( row.getCell(14).getDateCellValue());
            if(row.getCell(15).getCellType() == CellType.NUMERIC) {
            listOFgoods1.setDiscountUE((int) row.getCell(15).getNumericCellValue());
            }else if(row.getCell(15).getCellType() == CellType.STRING){
                String g = row.getCell(15).getStringCellValue().trim().replaceAll(" ","");
                System.out.println(g+"--"+g.length());

                try {
                    listOFgoods1.setDiscountUE((int) Double.parseDouble(row.getCell(15).getStringCellValue().replace("\\s+","")));

                } catch (NumberFormatException e) {
                    listOFgoods1.setDiscountUE(666);
                    e.printStackTrace();
                }


            }else{
                listOFgoods1.setDiscountUE(0);
            }
            if(row.getCell(17).getCellType() == CellType.NUMERIC) {
            listOFgoods1.setValueVUE((int) row.getCell(17).getNumericCellValue());
            }else if(row.getCell(17).getCellType() == CellType.STRING){
                listOFgoods1.setValueVUE(Integer.valueOf(row.getCell(17).getStringCellValue()));
            }else {
                listOFgoods1.setValueVUE(0);
            }

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
    @PostMapping("/importlistPhone")
    public String importlistPhone(@RequestParam("importlistPhone") MultipartFile importlistPhone, Model model) throws IOException {
        long start = System.currentTimeMillis();
        List<Phone_Smart> listPhone = new ArrayList<Phone_Smart>();
        XSSFWorkbook workbook = new XSSFWorkbook(importlistPhone.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        phoneRepositoriy.deleteAll();

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Phone_Smart listOPhone1 = new Phone_Smart();

            XSSFRow row = worksheet.getRow(i);


            listOPhone1.setMatrix_T2(row.getCell(0).getStringCellValue());
            listOPhone1.setBrend(row.getCell(1).getStringCellValue());
            listOPhone1.setModel( row.getCell(2).getStringCellValue());
            listOPhone1.setModel_GB( row.getCell(3).getStringCellValue());
            listOPhone1.setPhone( row.getCell(4).getStringCellValue());

            listPhone.add(listOPhone1);
            //  System.out.println(row.getCell(2).getNumericCellValue());
        }
        phoneRepositoriy.saveAll(listPhone);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        // System.out.println(df.format(new Date(timeWorkCode)));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());
        return "loading";
    }
    @PostMapping("/importlistPromo")
    public String importlistPromo(@RequestParam("importlistPromo") MultipartFile importlistPromo, Model model) throws IOException {
        long start = System.currentTimeMillis();
        List<price_promo> listPromo = new ArrayList<price_promo>();
        XSSFWorkbook workbook = new XSSFWorkbook(importlistPromo.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        promoRepositoriy.deleteAll();

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            price_promo listPromo1 = new price_promo();

            XSSFRow row = worksheet.getRow(i);

            listPromo1.setBrend(row.getCell(0).getStringCellValue());
            listPromo1.setModels(row.getCell(1).getStringCellValue());
            listPromo1.setPrice(String.valueOf(row.getCell(2).getNumericCellValue()));
            listPromo1.setPrice_promo(String.valueOf(row.getCell(3).getNumericCellValue()));
            listPromo1.setStartPromo( row.getCell(4).getDateCellValue());
            listPromo1.setEndPromo( row.getCell(5).getDateCellValue());
            listPromo1.setMarwel(String.valueOf(row.getCell(6).getNumericCellValue()));
            listPromo1.setTfn(String.valueOf(row.getCell(7).getNumericCellValue()));
            listPromo1.setVvp(String.valueOf(row.getCell(8).getNumericCellValue()));
            listPromo1.setMerlion(String.valueOf(row.getCell(9).getNumericCellValue()));


            listPromo.add(listPromo1);


            //  System.out.println(row.getCell(2).getNumericCellValue());

        }
        promoRepositoriy.saveAll(listPromo);
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
                if(row.getCell(1).getCellType() == CellType.STRING){
                    listVUE1.setImei(row.getCell(1).getStringCellValue());
                }else {
                    listVUE1.setImei(Long.toString((long) row.getCell(1).getNumericCellValue()));
                }
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
    @PostMapping("/importRemainingPhones")
    public String importRemainingPhones(@RequestParam("importRemainingPhones") MultipartFile importRemainingPhones,Model model) throws IOException, ParseException {
        remainingPhonesRepository.deleteAll();

        List<authorization_tt> ttArrayList = (List<authorization_tt>) authorizationTt.findAll();
        ArrayList<String> shopRaduga = new ArrayList<>();
        ArrayList<String> shopIskra = new ArrayList<>();

        shopIskra.add("Номенклатура");
        shopRaduga.add("Номенклатура");

        for (int i = 0;i<ttArrayList.size()-1;i++){
            shopRaduga.add(ttArrayList.get(i).getName());
            shopIskra.add(ttArrayList.get(i).getShopIskra());
        }

        List<RemainingPhones> listRemainingPhones = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importRemainingPhones.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        long start = System.currentTimeMillis();
        int numberOFcolumns = (worksheet.getRow(0).getPhysicalNumberOfCells());
        for (int l = 2; l < worksheet.getPhysicalNumberOfRows()-1; l++) {
            RemainingPhones remainingPhones = new RemainingPhones();
            String Remaining[] = new String[shopIskra.size()];
            for (int i = 0; i < shopIskra.size(); i++) {
                String h;
                for (int j = 0; j < numberOFcolumns; j++) {
                    if (worksheet.getRow(0).getCell(j).getStringCellValue().equals(shopIskra.get(i)) || worksheet.getRow(0).getCell(j).getStringCellValue().equals(shopRaduga.get(i))) {
                        XSSFRow row = worksheet.getRow(l);
                        if (row.getCell(j).getCellType() == CellType.STRING) {
                            h = row.getCell(j).getStringCellValue();
                        } else {
                            h = String.valueOf(row.getCell(j).getNumericCellValue());
                        }
                        Remaining[i] = h;
                    }
                }
                if (Remaining[i] == null) {
                    Remaining[i] = "0";
                }
            }
                remainingPhones.setNomenclature(Remaining[0]);
                remainingPhones.setCash((int) Double.parseDouble(Remaining[1]));
                remainingPhones.setSim((int) Double.parseDouble(Remaining[2]));
                remainingPhones.setCashT2((int) Double.parseDouble(Remaining[3]));
                remainingPhones.setSevernayaT2((int) Double.parseDouble(Remaining[4]));
                remainingPhones.setBagrationT2((int) Double.parseDouble(Remaining[5]));
                remainingPhones.setBeethovenT2((int) Double.parseDouble(Remaining[6]));
                remainingPhones.setZavertyaevaT2((int) Double.parseDouble(Remaining[7]));
                remainingPhones.setZyvaevskDIV((int) Double.parseDouble(Remaining[8]));
                remainingPhones.setZyvaevskT2((int) Double.parseDouble(Remaining[9]));
                remainingPhones.setNeftezavodskayaT2((int) Double.parseDouble(Remaining[10]));
                remainingPhones.setBolsherechyeT2((int) Double.parseDouble(Remaining[11]));
                remainingPhones.setZnamenskoyeMTS((int) Double.parseDouble(Remaining[12]));
                remainingPhones.setZnamenskoyeMegaFon((int) Double.parseDouble(Remaining[13]));
                remainingPhones.setKrutinkaT2((int) Double.parseDouble(Remaining[14]));
                remainingPhones.setKrutinka((int) Double.parseDouble(Remaining[15]));
                remainingPhones.setMoskalenkiMTS((int) Double.parseDouble(Remaining[16]));
                remainingPhones.setMoskalenkiT2((int) Double.parseDouble(Remaining[17]));
                remainingPhones.setOdessaDIV((int) Double.parseDouble(Remaining[18]));
                remainingPhones.setBolsherechyeMTS((int) Double.parseDouble(Remaining[19]));
                remainingPhones.setBigUkiT2((int) Double.parseDouble(Remaining[20]));
                remainingPhones.setZnamenskoeT2((int) Double.parseDouble(Remaining[21]));
                remainingPhones.setLuzinoT2((int) Double.parseDouble(Remaining[22]));
                remainingPhones.setMaryanovkaDIV((int) Double.parseDouble(Remaining[23]));
                remainingPhones.setMaryanovkaT2((int) Double.parseDouble(Remaining[24]));
                remainingPhones.setMuromtsevoT2((int) Double.parseDouble(Remaining[25]));
                remainingPhones.setMuromtsevoMTS((int) Double.parseDouble(Remaining[26]));
                remainingPhones.setTavricheskoeT2((int) Double.parseDouble(Remaining[27]));
                remainingPhones.setTavricheskoeMTS((int) Double.parseDouble(Remaining[28]));
                remainingPhones.setTaraT2((int) Double.parseDouble(Remaining[29]));
                remainingPhones.setSherbakulBee((int) Double.parseDouble(Remaining[30]));
                remainingPhones.setKolosovkaT2((int) Double.parseDouble(Remaining[31]));
                remainingPhones.setZyvaevskMTS((int) Double.parseDouble(Remaining[32]));
                remainingPhones.setSedelnikovoT2((int) Double.parseDouble(Remaining[33]));
                remainingPhones.setTevrizT2((int) Double.parseDouble(Remaining[34]));
                remainingPhones.setTevrizT2((int) Double.parseDouble(Remaining[35]));
                listRemainingPhones.add(remainingPhones);
            }

        remainingPhonesRepository.saveAll(listRemainingPhones);

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));

        return "loading";
    }
    private Date dateString(String stringCellValue) throws ParseException {

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss", Locale.ENGLISH);
        date = formatter.parse(stringCellValue);

        return date;
    }

    private Date dateDate(String stringCellValue) throws ParseException {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
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
