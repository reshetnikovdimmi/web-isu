package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.Bonuses;
import com.myisu_1.isu.models.Marwel.*;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.BonusesServise;
import com.myisu_1.isu.service.MarwelPromoServise;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


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
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PriceRepositoriy priceRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private BonusesServise bonusesServise;
    @Autowired
    private MarwelPromoServise marwelPromoServise;
    @Autowired
    private RemainingPhonesMarwelServise remainingPhonesMarwelServise;

    @GetMapping("/Marwel")
    public String Marwel(Model model) {
        model.addAttribute("promoCode", marwelPromoRepositoriy.findAll());
        model.addAttribute("promoCodeDistinct", marwelPromoRepositoriy.getDistingMarvelPromo());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        model.addAttribute("noPhone", marwelPromoServise.noPhone());
        model.addAttribute("NoClassifier", marwelPromoServise.NoClassifier());
        return "Marwel";
    }

    @ResponseBody
    @RequestMapping(value = "update_MarClasif/{id}", method = RequestMethod.GET)
    public Optional<MarvelClassifier> update_MarClasif(@PathVariable("id") int id) {
        return marvelClassifierRepositoriy.findById(id);
    }

    @PostMapping("/add_MarwClasif")
    public String add_MarwClasif(@RequestParam int IDupdateMarClasif,
                                 @RequestParam String RainbowNomenclature,
                                 @RequestParam String ManufacturersArticle,
                                 @RequestParam String Name,
                                 Model model) {
        if (IDupdateMarClasif != 0) {
            marvelClassifierRepositoriy.save((new MarvelClassifier(IDupdateMarClasif, RainbowNomenclature, ManufacturersArticle, Name)));
        } else {
            marvelClassifierRepositoriy.save((new MarvelClassifier(RainbowNomenclature, ManufacturersArticle, Name)));
        }
        model.addAttribute("promoCode", marwelPromoRepositoriy.findAll());
        model.addAttribute("promoCodeDistinct", marwelPromoRepositoriy.getDistingMarvelPromo());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        model.addAttribute("noPhone", marwelPromoServise.noPhone());
        model.addAttribute("NoClassifier", marwelPromoServise.NoClassifier());
        return "Marwel";
    }

    @PostMapping("/delet_MarClasif")
    public String delet_MarClasif(@RequestParam int IDMarClasif, Model model) {
        marvelClassifierRepositoriy.deleteById(IDMarClasif);
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        return "Marwel";
    }

    @RequestMapping(value = "/promoCodeDistinct", method = RequestMethod.POST)
    private ResponseEntity<List<Bonuses>> promoCodeDistinct(@RequestBody MarvelPromo marvelPromo, Model model) {
        return new ResponseEntity<>(bonusesServise.marvelReportings(marvelPromo), HttpStatus.OK);
    }

    @PostMapping("/portalMarwel")
    public String portalMarwel(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd") Date stop, Model model) throws ParseException {
        model.addAttribute("artNaProdOst", marwelPromoServise.reportUploadPortal(start, stop));
        model.addAttribute("article_imei", marwelPromoServise.articleImeiList(start, stop));
        model.addAttribute("promoCode", marwelPromoRepositoriy.findAll());
        model.addAttribute("promoCodeDistinct", marwelPromoRepositoriy.getDistingMarvelPromo());
        model.addAttribute("Poco", marwelPromoServise.forRomaPoco(start, stop, "Poco"));
        model.addAttribute("Xiaomi", marwelPromoServise.forRomaXiaomi(start, stop, "Xiaomi"));
        model.addAttribute("forRomaShares", marwelPromoServise.forRomaShares(start, stop));
        model.addAttribute("noPhone", marwelPromoServise.noPhone());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        model.addAttribute("NoClassifier", marwelPromoServise.NoClassifier());
        return "Marwel";
    }

    @PostMapping("/importRemainingPhonesMarwel")
    public String importRemainingPhones(@RequestParam("importRemainingPhonesMarwel") MultipartFile importRemainingPhonesMarwel, Model model) throws IOException, ParseException {
        remainingPhonesMarwelServise.deleteAll();
        List<RemainingPhonesMarwel> listRemainingPhonesMarwel = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importRemainingPhonesMarwel.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            RemainingPhonesMarwel listRemainingPhonesMarwel1 = new RemainingPhonesMarwel();
            XSSFRow row = worksheet.getRow(i);
            listRemainingPhonesMarwel1.setCharacteristic(row.getCell(0).getStringCellValue());
            listRemainingPhonesMarwel1.setModel(row.getCell(1).getStringCellValue());
            listRemainingPhonesMarwel.add(listRemainingPhonesMarwel1);
        }
        remainingPhonesMarwelServise.saveAll(listRemainingPhonesMarwel);
        model.addAttribute("NoClassifier", marwelPromoServise.NoClassifier());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        model.addAttribute("noPhone", marwelPromoServise.noPhone());
        model.addAttribute("promoCode", marwelPromoRepositoriy.findAll());
        model.addAttribute("promoCodeDistinct", marwelPromoRepositoriy.getDistingMarvelPromo());
        return "Marwel";
    }
}
