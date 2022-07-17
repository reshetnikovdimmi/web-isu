package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.*;
import com.myisu_1.isu.models.Marwel.Article_Imei;
import com.myisu_1.isu.models.Marwel.MarvelClassifier;
import com.myisu_1.isu.repo.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
public class MarwelController {

    @Autowired
    private MarwelPromoRepositoriy marwelPromoRepositoriy;
    @Autowired
    private MarvelClassifierRepositoriy marvelClassifierRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;

    List<MarvelPromo> promoMarwel;
    List<RemainingPhonesMarwel> listRemainingPhonesMarwel;
    List<Suppliers> suppliersList;
    List<Sales> sales;
    List<Article_Imei> article_imeiList;
    List<MarvelClassifier> marvelClassifierList;
    @GetMapping("/Marwel")

    public String Marwel(Model model) {
        marvelClassifierList = marvelClassifierRepositoriy.findAll();
        suppliersList = suppliersRepositoriy.findAll();
        sales = (List<Sales>) salesRepositoriy.findAll();

        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        return "Marwel";
    }

    private List<Distinct> promoCodeDistinct() {

        List<Distinct> listDistinct = new ArrayList<>();
        HashSet<String> hashDistinct = new HashSet<>();
        for(int i=0;i<promoMarwel.size();i++){
            hashDistinct.add(promoMarwel.get(i).getPromoCode());
        }
        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext())

        listDistinct.add(new Distinct(i.next()));
        return  listDistinct;
    }

    private List<MarvelPromo> promoCode() {
        promoMarwel = (List<MarvelPromo>) marwelPromoRepositoriy.findAll();
        List<MarvelPromo> promoCode = new ArrayList<>();

        for (int i = 0; i < promoMarwel.size(); i++) {

            promoCode.add(new MarvelPromo(
                    promoMarwel.get(i).getId(),
                    promoMarwel.get(i).getPromoCode(),
                    promoMarwel.get(i).getStartPromo(),
                    promoMarwel.get(i).getEndPromo(),
                    promoMarwel.get(i).getArticleNumber(),
                    promoMarwel.get(i).getVision(),
                    promoMarwel.get(i).getNewVision(),
                    promoMarwel.get(i).getDiscount(),
                    promoMarwel.get(i).getCompensation(),
                    promoMarwel.get(i).getCollecting(),
                    promoMarwel.get(i).getStatus()

            ));


        }
        return promoCode;
    }
    @PostMapping("/portalMarwel")
    public String entrance(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date stop, Model model) throws ParseException {
       article_imeiList = new ArrayList<>();
        HashSet<String> phone = new HashSet<>();
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> phones = new ArrayList<>();
  for (int j=0;j<suppliersList.size();j++) {
      for (int i = 0;i<sales.size();i++){
            if(suppliersList.get(j).getSuppliers().equals("МАРВЕЛ КТ ООО")&&
                    sales.get(i).getDateSales().getTime()>=start.getTime()&&
                    sales.get(i).getDateSales().getTime()<=stop.getTime()&&
                    suppliersList.get(j).getImei().equals(sales.get(i).getImeis())){
                        if(sales.get(i).getNomenclature().contains("Xiaomi") || sales.get(i).getNomenclature().contains("Redmi") || sales.get(i).getNomenclature().contains("Mi True")){
                           // System.out.println(sales.get(i).getNomenclature()+"--->"+sales.get(i).getImeis());
                            list.add(sales.get(i).getNomenclature());
                            phone.add(sales.get(i).getNomenclature());
                            for (int l = 0;l<marvelClassifierList.size();l++){
                                if (sales.get(i).getNomenclature().equals(marvelClassifierList.get(l).getRainbowNomenclature())){
                                    article_imeiList.add(new Article_Imei(marvelClassifierList.get(l).getManufacturersArticle(),sales.get(i).getImeis()));
                                }
                            }
                        }
                    }
      }
      for (int i = 0; i < listRemainingPhonesMarwel.size(); i++) {
         if(suppliersList.get(j).getSuppliers().equals("МАРВЕЛ КТ ООО")&&suppliersList.get(j).getImei().equals(listRemainingPhonesMarwel.get(i).getCharacteristic())){
            if(listRemainingPhonesMarwel.get(i).getModel().contains ("Xiaomi") || listRemainingPhonesMarwel.get(i).getModel().contains ("Redmi") || listRemainingPhonesMarwel.get(i).getModel().contains ("Mi True")){
            list1.add(listRemainingPhonesMarwel.get(i).getModel());
            phone.add(listRemainingPhonesMarwel.get(i).getModel());
            }
         }
      }
  }

        Map<String, Long> frequency = list.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        Map<String, Long> frequency1 = list1.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        Iterator<String> i = phone.iterator();
        while (i.hasNext())
            phones.add(i.next());


        model.addAttribute("article_imei", article_imeiList);
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());

        return "Marwel";
    }



    @PostMapping("/importRemainingPhonesMarwel")
    public String importRemainingPhones(@RequestParam("importRemainingPhonesMarwel") MultipartFile importRemainingPhonesMarwel, Model model) throws IOException, ParseException {

        listRemainingPhonesMarwel = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importRemainingPhonesMarwel.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            RemainingPhonesMarwel listRemainingPhonesMarwel1 = new RemainingPhonesMarwel();

            XSSFRow row = worksheet.getRow(i);

            listRemainingPhonesMarwel1.setCharacteristic(row.getCell(0).getStringCellValue());
            listRemainingPhonesMarwel1.setModel(row.getCell(1).getStringCellValue());

            listRemainingPhonesMarwel.add(listRemainingPhonesMarwel1);

        }

        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        return "Marwel";
    }
}
