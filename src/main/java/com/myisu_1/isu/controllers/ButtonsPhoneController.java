package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteDistributionButton;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.models.Phone.ButtonsPhone;
import com.myisu_1.isu.models.Phone.DistributionPhone;
import com.myisu_1.isu.repo.ButtonsPhoneRepositoriy;
import com.myisu_1.isu.service.ButtonsPhoneServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ButtonsPhoneController {
    @Autowired
    private ButtonsPhoneRepositoriy buttonsPhoneRepositoriy;
    @Autowired
    private ButtonsPhoneServise buttonsPhoneServise;
    @GetMapping("/ButtonsPhone")
    public String bonuses(Model model) {

        model.addAttribute("Phone", buttonsPhoneServise.findAllButtonsPhone());
        return "ButtonsPhone";
    }
    @GetMapping("/ButtonsPhoneDistribution")
    public String ButtonsPhoneDistribution(Model model) {
        model.addAttribute("Requirement",buttonsPhoneServise.tabletableRequirement());
        model.addAttribute("Phone", buttonsPhoneServise.graduationPhone());
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
    public Map<String,Map<String,String>> price() {

        return buttonsPhoneServise.graduationPhone();
    }

    @RequestMapping(value="/tableShopRemanisSele/{brend}", method=RequestMethod.GET)
    private String tableShopRemanisSele(@PathVariable("brend")  String brend, Model model) {

       model.addAttribute("graduation",buttonsPhoneServise.tableShopRemanisSele(brend));

        return "ButtonsPhoneDistribution::graduation";
    }
    @RequestMapping(value="/tableShopRemanisCash/{brend}", method=RequestMethod.GET)
    private String tableShopRemanisCash(@PathVariable("brend")  String brend, Model model) {

        model.addAttribute("graduation",buttonsPhoneServise.tableShopRemanisCash(brend));

        return "ButtonsPhoneDistribution::RemanisCash";
    }
    @ResponseBody
    @RequestMapping(value="/tableDistributionButton/{shop}", method=RequestMethod.GET)
    private Map<String, Map<String, Map<String, String>>> tableDistributionButton(@PathVariable("shop")  String shop, Model model) {

        return buttonsPhoneServise.tableShopRemanis(shop);
    }
    @ResponseBody
    @RequestMapping(value = "tableShopRemanis/{shop}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> tableShopRemanis(@PathVariable("shop")  String shop) {

        return buttonsPhoneServise.tableShopRemanis(shop);
    }
    @ResponseBody
    @RequestMapping(value = "tableUpDistributionButton/{shop}/{models}/{quantity}/{brend}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> tableUpDistributionButton(@PathVariable("shop")  String shop, @PathVariable("models")  String models,@PathVariable("quantity")  String quantity,@PathVariable("brend")  String brend) {

        return buttonsPhoneServise.tableUpDistributionButton(shop,models,quantity,brend);
    }
    @GetMapping("/exselDistributionButton")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionButton.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionButton.exportPrisePromoFile(buttonsPhoneServise.exselDistributionButto(), buttonsPhoneRepositoriy.getModelsButton());

        IOUtils.copy(inputStream, response.getOutputStream());


    }
    @GetMapping("/CardsArrayExpDate")
    public String JS(Model model) {

        return "CardsArrayExpDate";
    }
}
