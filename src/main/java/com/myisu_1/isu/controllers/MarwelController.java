package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.*;
import com.myisu_1.isu.models.Marwel.*;
import com.myisu_1.isu.repo.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
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
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PriceRepositoriy priceRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;


    List<MarvelPromo> promoMarwel;
    List<price_promo> price_promoList;
    List<RemainingPhonesMarwel> listRemainingPhonesMarwel;
    List<Suppliers> suppliersList;
    List<Sales> sales;
    List<Article_Imei> article_imeiList;
    List<MarvelClassifier> marvelClassifierList;
    List<Phone_Smart> phone_smarts;
    List<String> listDistinct;
    List<retail_price> retail_prices;


    @GetMapping("/Marwel")

    public String Marwel(Model model) {
        price_promoList = (List<price_promo>) promoRepositoriy.findAll();
        retail_prices = (List<retail_price>) priceRepositoriy.findAll();
        phone_smarts = phoneRepositoriy.findAll();
        marvelClassifierList = marvelClassifierRepositoriy.findAll();
        suppliersList = suppliersRepositoriy.findAll();
        sales = (List<Sales>) salesRepositoriy.findAll();
        promoMarwel = (List<MarvelPromo>) marwelPromoRepositoriy.findAll();
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        return "Marwel";
    }

    @ResponseBody
    @RequestMapping(value = "update_MarClasif/{id}", method = RequestMethod.GET)
    public Optional<MarvelClassifier> update_MarClasif(@PathVariable("id") int id) {
        System.out.println(id);
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

        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        return "Marwel";
    }

    @PostMapping("/delet_MarClasif")
    public String delet_MarClasif(@RequestParam int IDMarClasif, Model model) {
        marvelClassifierRepositoriy.deleteById(IDMarClasif);
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        return "Marwel";
    }

    @PostMapping("/promoCodeDistinct")
    public String promoCodeDistinct(@RequestParam String promoCode, Model model) {

        String[] words;
        List<MarvelReporting> marvelReportings = new ArrayList<>();

        for (int i = 0; i < promoMarwel.size(); i++) {

            if (promoCode.equals(promoMarwel.get(i).getPromoCode())) {

                words = promoMarwel.get(i).getArticleNumber().toUpperCase().replace("+", " ").replace("GB", "").split(" ");
                for (int j = 0; j < sales.size(); j++) {
                    if (sales.get(j).getDateSales().getTime() >= promoMarwel.get(i).getStartPromo().getTime() && sales.get(j).getDateSales().getTime() <= promoMarwel.get(i).getEndPromo().getTime()) {
                        for (int l = 0; l < suppliersList.size(); l++) {
                            if (suppliersList.get(l).getImei().equals(sales.get(j).getImeis()) &&
                                    suppliersList.get(l).getSuppliers().equals("МАРВЕЛ КТ ООО") &&
                                    marvelPromSales(sales.get(j).getDateSales(), words, sales.get(j).getNomenclature()) == true) {
                                System.out.println(sales.get(j).getNomenclature() + "--" + sales.get(j).getImeis());
                                marvelReportings.add(new MarvelReporting(sales.get(j).getNomenclature(), sales.get(j).getImeis(), String.valueOf(sales.get(j).getDateSales()), promoMarwel.get(i).getStartPromo() + "<-->" + promoMarwel.get(i).getEndPromo(), promoMarwel.get(i).getPromoCode()));


                            }
                        }
                    }
                }


            }
        }
        model.addAttribute("marvelReportings", marvelReportings);
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        return "Marwel";
    }

    private boolean marvelPromSales(Date dateSales, String[] words, String nomenclature) {
        String[] nomenclatures;

        int cou = 0;
        nomenclatures = nomenclature.replaceAll("/", " ").toUpperCase().replaceAll("GB", "").split(" ");


        for (String nomenclaturen : nomenclatures) {


            for (String word : words) {

                if (word.equals(nomenclaturen)) {

                    cou++;

                }

            }
        }
        if (cou == words.length) {
            for (int i = 0; i < price_promoList.size(); i++) {
                if (dateSales.getTime() >= price_promoList.get(i).getStartPromo().getTime() && dateSales.getTime() <= price_promoList.get(i).getEndPromo().getTime() && nomenclature.contains(price_promoList.get(i).getModels())) {
                    return true;
                }
            }

        }

        return false;
    }

    private List<Distinct> promoCodeDistinct() {

        List<Distinct> listDistinct = new ArrayList<>();
        HashSet<String> hashDistinct = new HashSet<>();
        for (int i = 0; i < promoMarwel.size(); i++) {
            hashDistinct.add(promoMarwel.get(i).getPromoCode());
        }
        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext())

            listDistinct.add(new Distinct(i.next()));
        return listDistinct;
    }

    private List<MarvelPromo> promoCode() {

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
        List<String> phonesUnique = new ArrayList<>();
        List<ArtNaProdOst> uniquelist = new ArrayList<>();
        List<Distinct> NoClassifier = new ArrayList<>();

        for (int j = 0; j < suppliersList.size(); j++) {
            for (int i = 0; i < sales.size(); i++) {
                if (suppliersList.get(j).getSuppliers().equals("МАРВЕЛ КТ ООО") &&
                        sales.get(i).getDateSales().getTime() >= start.getTime() &&
                        sales.get(i).getDateSales().getTime() <= stop.getTime() &&
                        suppliersList.get(j).getImei().equals(sales.get(i).getImeis())) {
                    if (sales.get(i).getNomenclature().contains("Xiaomi") || sales.get(i).getNomenclature().contains("Redmi") || sales.get(i).getNomenclature().contains("Mi True")) {
                        list.add(sales.get(i).getNomenclature());
                        phone.add(sales.get(i).getNomenclature());
                        for (int l = 0; l < marvelClassifierList.size(); l++) {
                            if (sales.get(i).getNomenclature().equals(marvelClassifierList.get(l).getRainbowNomenclature())) {
                                article_imeiList.add(new Article_Imei(marvelClassifierList.get(l).getManufacturersArticle(), sales.get(i).getImeis()));
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < listRemainingPhonesMarwel.size(); i++) {
                if (suppliersList.get(j).getSuppliers().equals("МАРВЕЛ КТ ООО") && suppliersList.get(j).getImei().equals(listRemainingPhonesMarwel.get(i).getCharacteristic())) {
                    if (listRemainingPhonesMarwel.get(i).getModel().contains("Xiaomi") || listRemainingPhonesMarwel.get(i).getModel().contains("Redmi") || listRemainingPhonesMarwel.get(i).getModel().contains("Mi True")) {
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
            phonesUnique.add(i.next());

        for (int j = 0; j < phonesUnique.size(); j++) {

            ArtNaProdOst artNaProdOst = new ArtNaProdOst();
            for (Map.Entry<String, Long> item : frequency.entrySet()) {
                if (phonesUnique.get(j).equals(item.getKey())) {

                    artNaProdOst.setSales(String.valueOf(item.getValue()));
                }


            }
            for (Map.Entry<String, Long> item : frequency1.entrySet()) {
                if (phonesUnique.get(j).equals(item.getKey())) {

                    artNaProdOst.setRemains(String.valueOf(item.getValue()));
                }

            }
            for (int l = 0; l < marvelClassifierList.size(); l++) {
                if (phonesUnique.get(j).equals(marvelClassifierList.get(l).getRainbowNomenclature())) {
                    artNaProdOst.setArticle(marvelClassifierList.get(l).getManufacturersArticle());
                    artNaProdOst.setName(marvelClassifierList.get(l).getName());
                }

            }
            uniquelist.add(artNaProdOst);
        }
        for (int j = 0; j < uniquelist.size(); j++) {
            if (uniquelist.get(j).getArticle() == null) {
                NoClassifier.add(new Distinct(phonesUnique.get(j)));
            }

        }

        model.addAttribute("NoClassifier", NoClassifier);
        model.addAttribute("artNaProdOst", uniquelist);
        model.addAttribute("article_imei", article_imeiList);
        model.addAttribute("promoCode", promoCode());
        model.addAttribute("promoCodeDistinct", promoCodeDistinct());
        model.addAttribute("Poco", forRoma(start, stop, "Poco", "Poco", "Poco"));
        model.addAttribute("Xiaomi", forRoma(start, stop, "Xiaomi", "Mi True", "Redmi"));
        model.addAttribute("forRomaShares", forRomaShares(start, stop));
        model.addAttribute("noPhone", noPhone());
        model.addAttribute("MarwClassif", marvelClassifierRepositoriy.findAll());
        return "Marwel";
    }

    private Object noPhone() {
        List<Distinct> listDistinct = new ArrayList<>();
        HashSet<String> hashDistinct = new HashSet<>();
        List<String> phone = new ArrayList<>();
        List<String> remains = new ArrayList<>();
        for (int i = 0; i < phone_smarts.size(); i++) {
            phone.add(phone_smarts.get(i).getModel());
        }
        for (int i = 0; i < listRemainingPhonesMarwel.size(); i++) {
            remains.add(listRemainingPhonesMarwel.get(i).getModel());
            if (!phone.contains(listRemainingPhonesMarwel.get(i).getModel())) {
                hashDistinct.add(listRemainingPhonesMarwel.get(i).getModel());
            }
        }
        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext())

            listDistinct.add(new Distinct(i.next()));
        return listDistinct;
    }

    private Object forRomaShares(Date start, Date stop) {
        List<ForRoma> listforRoma = new ArrayList<>();
        List<Integer> sumSum = new ArrayList<>();
        List<Integer> resmanisSum = new ArrayList<>();
        listDistinct = new ArrayList<>();
        HashSet<String> hashDistinct = new HashSet<>();
        for (int i = 0; i < phone_smarts.size(); i++) {
            if (!phone_smarts.get(i).getPhone().isEmpty()) {
                hashDistinct.add(phone_smarts.get(i).getPhone());
            }
        }
        Iterator<String> i = hashDistinct.iterator();
        while (i.hasNext())

            listDistinct.add(i.next());
        for (int j = 0; j < listDistinct.size(); j++) {

            int cou = 0;
            int couPrices = 0;

            for (int l = 0; l < phone_smarts.size(); l++) {
                if (listDistinct.get(j).equals(phone_smarts.get(l).getPhone())) {
                    for (int z = 0; z < sales.size(); z++) {
                        if (phone_smarts.get(l).getModel().equals(sales.get(z).getNomenclature()) && sales.get(z).getDateSales().getTime() >= start.getTime() && sales.get(z).getDateSales().getTime() <= stop.getTime()) {
                            cou++;
                            for (int x = 0; x < retail_prices.size(); x++) {
                                if (sales.get(z).getNomenclature().equals(retail_prices.get(x).getName())) {
                                    couPrices = couPrices + (int) Double.parseDouble(retail_prices.get(x).getPrice().replaceAll(",", ".").replaceAll("\\s+", ""));
                                }
                            }
                        }
                    }
                }
            }

            sumSum.add(couPrices);

            resmanisSum.add(cou);

        }

        Double c = Double.valueOf(sumSum.stream().mapToLong(Integer::longValue).sum());
        Double b = Double.valueOf(resmanisSum.stream().mapToLong(Integer::longValue).sum());

        for (int j = 0; j < listDistinct.size(); j++) {
            ForRoma forRoma = new ForRoma();
            forRoma.setPhone(listDistinct.get(j));
            forRoma.setQuantity(String.valueOf(resmanisSum.get(j)));
            forRoma.setAmount(String.valueOf(sumSum.get(j)));
            forRoma.setThings(String.format("%.2f", sumSum.get(j) / c * 100) + "%");
            forRoma.setRubles(String.format("%.2f", resmanisSum.get(j) / b * 100) + "%");
            listforRoma.add(forRoma);
        }
        return listforRoma;
    }

    private List<ArtNaProdOst> forRoma(Date start, Date stop, String xiaomi, String poco, String redmi) {
        article_imeiList = new ArrayList<>();
        HashSet<String> phone = new HashSet<>();
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> phonesUnique = new ArrayList<>();
        List<ArtNaProdOst> uniquelist = new ArrayList<>();
        List<Distinct> NoClassifier = new ArrayList<>();
        for (int j = 0; j < suppliersList.size(); j++) {
            for (int i = 0; i < sales.size(); i++) {
                if (sales.get(i).getDateSales().getTime() >= start.getTime() &&
                        sales.get(i).getDateSales().getTime() <= stop.getTime() &&
                        suppliersList.get(j).getImei().equals(sales.get(i).getImeis())) {
                    if (sales.get(i).getNomenclature().contains("Xiaomi") || sales.get(i).getNomenclature().contains("Redmi") || sales.get(i).getNomenclature().contains("Mi True")) {
                        list.add(sales.get(i).getNomenclature());
                        phone.add(sales.get(i).getNomenclature());
                    }
                }
            }
        }
        for (int i = 0; i < listRemainingPhonesMarwel.size(); i++) {
            if (listRemainingPhonesMarwel.get(i).getModel().contains("Xiaomi") || listRemainingPhonesMarwel.get(i).getModel().contains("Redmi") || listRemainingPhonesMarwel.get(i).getModel().contains("Mi True")) {
                list1.add(listRemainingPhonesMarwel.get(i).getModel());
                phone.add(listRemainingPhonesMarwel.get(i).getModel());
            }
        }

        Map<String, Long> frequency = list.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        Map<String, Long> frequency1 = list1.stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()));

        Iterator<String> i = phone.iterator();
        while (i.hasNext())
            phonesUnique.add(i.next());

        for (int j = 0; j < phonesUnique.size(); j++) {

            if (phonesUnique.get(j).contains(poco) || phonesUnique.get(j).contains(xiaomi) || phonesUnique.get(j).contains(redmi)) {
                ArtNaProdOst artNaProdOst = new ArtNaProdOst();
                for (Map.Entry<String, Long> item : frequency.entrySet()) {
                    if (phonesUnique.get(j).equals(item.getKey())) {
                        artNaProdOst.setSales(String.valueOf(item.getValue()));
                    }
                }
                for (Map.Entry<String, Long> item : frequency1.entrySet()) {
                    if (phonesUnique.get(j).equals(item.getKey())) {
                        artNaProdOst.setRemains(String.valueOf(item.getValue()));

                    }
                }
                for (int l = 0; l < marvelClassifierList.size(); l++) {
                    if (phonesUnique.get(j).equals(marvelClassifierList.get(l).getRainbowNomenclature())) {

                        artNaProdOst.setName(marvelClassifierList.get(l).getName());
                    }
                }
                artNaProdOst.setArticle(phonesUnique.get(j));
                uniquelist.add(artNaProdOst);
            }

        }
        for (int z = 0; z < uniquelist.size(); z++) {
            if (uniquelist.get(z).getArticle().contains("Poco") && poco.equals("Mi True")) {
                uniquelist.remove(z);

            }

        }

        return uniquelist;
    }

    @PostMapping("/importRemainingPhonesMarwel")
    public String importRemainingPhones(@RequestParam("importRemainingPhonesMarwel") MultipartFile importRemainingPhonesMarwel, Model model) throws IOException, ParseException {

        listRemainingPhonesMarwel = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(importRemainingPhonesMarwel.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
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
