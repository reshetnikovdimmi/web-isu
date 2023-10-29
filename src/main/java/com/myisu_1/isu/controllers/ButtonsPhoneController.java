package com.myisu_1.isu.controllers;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionButton;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionImei;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.repo.SuppliersRepositoriy;
import com.myisu_1.isu.service.ButtonsPhoneServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ButtonsPhoneController {
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private ButtonsPhoneServise buttonsPhoneServise;

    @Autowired
    private SuppliersRepositoriy suppliersRepositoriy;
    @GetMapping("/ButtonsPhone")
    public String bonuses(Model model) {

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());

        return "ButtonsPhone";
    }
    @GetMapping("/ButtonsPhoneDistribution")
    public String ButtonsPhoneDistribution(Model model) {


        model.addAttribute("Phone", buttonsPhoneServise.remainsCashGroup());
        model.addAttribute("Requirement", buttonsPhoneServise.remanisButtonShop());
        return "ButtonsPhoneDistribution";
    }
    @ResponseBody
    @RequestMapping(value = "update_ButtonsPhone/{id}", method = RequestMethod.GET)
    public Optional<ButtonsPhone> update(@PathVariable("id") int id) {

        return buttonsPhoneRepositoriy.findById(id);
    }


    @PostMapping("/add_ButtonsPhone")
    public String add_ButtonsPhone(@RequestParam int ID,@RequestParam String Brend,@RequestParam String Model,Model model) {

        if (ID != 0) {
            buttonsPhoneRepositoriy.save((new ButtonsPhone(ID,Brend, Model)));
        } else {
            buttonsPhoneRepositoriy.save((new ButtonsPhone(Brend, Model)));
        }

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }
    @PostMapping("/delet_ButtonsPhone")
    public String delet(@RequestParam int IDButtonPhone, Model model) {
        buttonsPhoneRepositoriy.deleteById(IDButtonPhone);

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }

    @ResponseBody
    @RequestMapping(value = "ButtonsPhones", method = RequestMethod.GET)
    public OrderRecommendations price() {

        return buttonsPhoneServise.remainsCashGroup();
    }

    @RequestMapping(value="/tableShopRemanisSele/{brend}", method=RequestMethod.GET)
    private String tableShopRemanisSele(@PathVariable("brend")  String brend, Model model) {

      // model.addAttribute("graduation",buttonsPhoneServise.tableShopRemanisSele(brend));
        OrderRecommendations or = buttonsPhoneServise.tableShopRemanisSele(brend);

        model.addAttribute("graduation", or.getIndicatorPhoneSach().stream().filter(r -> r.getGroup().equals(brend)).collect(Collectors.toList()));
        model.addAttribute("RemanisPhoneGroup", or.getRemainsGroupShop().stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(brend)).collect(Collectors.toList()));
        return "ButtonsPhoneDistribution::graduation";
    }
    @PostMapping("/DistributionButton")
    private ResponseEntity<OrderRecommendations> distribution(@RequestBody OrderRecommendations OR) {
        return new ResponseEntity<>(buttonsPhoneServise.distribution(OR), HttpStatus.OK);
    }

    @RequestMapping(value="/tableDistributionButton/{shop}", method=RequestMethod.GET)
    private String tableDistributionButton(@PathVariable("shop")  String shop, Model model) {
        model.addAttribute("TableDistributionPhone", buttonsPhoneServise.tableShopRemanis(shop));
        return "ButtonsPhoneDistribution::TableDistributionPhone";
    }



    @GetMapping("/exselDistributionButton")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionButton.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionPhones.exportPrisePromoFile(buttonsPhoneServise.exselDistributionButto());

        IOUtils.copy(inputStream, response.getOutputStream());

    }
    @GetMapping("/CardsArrayExpDate")
    public String JS(Model model) {

        return "CardsArrayExpDate";
    }

    @ResponseBody
    @RequestMapping(value = "/imeiDistribution/{imei}", method = RequestMethod.GET)
    public String ImeiDistribution(@PathVariable String imei, Model model) {

        System.out.println(suppliersRepositoriy.imeiDistribution(imei));


        return suppliersRepositoriy.imeiDistribution(imei);
    }
    @GetMapping("/exselDistributionImei")
    public void exselDistributionImei(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionImei.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionImei.exportPrisePromoFile(suppliersRepositoriy.findAll());

        IOUtils.copy(inputStream, response.getOutputStream());

    }
}
