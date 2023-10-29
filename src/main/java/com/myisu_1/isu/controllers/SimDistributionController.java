package com.myisu_1.isu.controllers;


import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionSim;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.SimDistributionServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SimDistributionController {
    @Autowired
    private PostRepositoriy authorization_shop;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private RemanisSimRepository remanisSimrepository;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private SimDistributionServise simDistributionServise;




    //=======SimDistributionNew.html
    @GetMapping("/SimDistributionsNew")
    public String SimDistributionNew (Model model) {

        model.addAttribute("Accessories", simDistributionServise.remainsCash());

        return "SimDistributionsNew";

    }
    @RequestMapping(value = "/table_simT2/{nameSim}", method = RequestMethod.GET)

    private String accessoriesCategoryMaxSale(@PathVariable("nameSim") String nameSim, Model model) {
        OrderRecommendations or = simDistributionServise.nameSimShop();

        model.addAttribute("RemanSimCash", or.getIndicatorPhoneSach().stream().filter(r -> r.getGroup().equals(nameSim)).collect(Collectors.toList()));
        model.addAttribute("RemanisPhoneGroup", or.getRemainsGroupShop().stream().filter(r ->r.getGroup()!=null && r.getGroup().equals(nameSim)).collect(Collectors.toList()));
        return "SimDistributionsNew::RemanSimCash";

    }
    @RequestMapping(value = "/NameSimShopMTS/{nameSim}/{view}", method = RequestMethod.GET)

    private String nameSimShopMTS(@PathVariable("nameSim") String nameSim,@PathVariable("view") String view, Model model) {

        model.addAttribute("NameSimShopMTS", simDistributionServise.nameSimShop());
        return "SimDistributionsNew::NameSimShopMTS";

    }
    @RequestMapping(value = "/NameSimShopT2mult/{nameSim}/{view}", method = RequestMethod.GET)

    private String nameSimShopT2mult(@PathVariable("nameSim") String nameSim,@PathVariable("view") String view, Model model) {

        model.addAttribute("NameSimShopT2mult", simDistributionServise.nameSimShop());
        return "SimDistributionsNew::NameSimShopT2mult";

    }
    @RequestMapping(value = "/RemanSimCash/{nameSim}", method = RequestMethod.GET)

    private String remanSimCash(@PathVariable("nameSim") String nameSim, Model model) {

        model.addAttribute("RemanSimCash", simDistributionServise.remanSimCash(nameSim.replaceAll("_","/")));
        return "SimDistributionsNew::RemanSimCash";

    }
    @RequestMapping(value = "/RemanSimCashT2mult/{nameSim}", method = RequestMethod.GET)

    private String remanSimCashT2mult(@PathVariable("nameSim") String nameSim, Model model) {

        model.addAttribute("RemanSimCashT2mult", simDistributionServise.remanSimCash(nameSim.replaceAll("_","/")));
        return "SimDistributionsNew::RemanSimCashT2mult";

    }
    @RequestMapping(value = "/RemanSimCashMTS/{nameSim}", method = RequestMethod.GET)

    private String remanSimCashMTS(@PathVariable("nameSim") String nameSim, Model model) {

        model.addAttribute("RemanSimCashMTS", simDistributionServise.remanSimCash(nameSim.replaceAll("_","/")));
        return "SimDistributionsNew::RemanSimCashMTS";

    }
    @RequestMapping(value = "/UpdateRemanisCash/{grop}", method = RequestMethod.GET)

    private String updateRemanisCash(@PathVariable("grop") String grop, Model model) {

        model.addAttribute("RemanSimCash", simDistributionServise.updateRemanisCash(grop));
        return "SimDistributionsNew::RemanSimCash";

    }
    @RequestMapping(value = "/UpdateRemanisCashT2mult/{grop}", method = RequestMethod.GET)

    private String updateRemanisCashT2mult(@PathVariable("grop") String grop, Model model) {

        model.addAttribute("RemanSimCashT2mult", simDistributionServise.updateRemanisCash(grop));
        return "SimDistributionsNew::RemanSimCashT2mult";

    }
    @RequestMapping(value = "/UpdateRemanisCashMTS/{grop}", method = RequestMethod.GET)

    private String updateRemanisCashMTS(@PathVariable("grop") String grop, Model model) {

        model.addAttribute("RemanSimCashMTS", simDistributionServise.updateRemanisCash(grop));
        return "SimDistributionsNew::RemanSimCashMTS";

    }

    @RequestMapping(value = "TableDistributionSim/{shop}", method = RequestMethod.GET)
    private String tableDistributionButton(@PathVariable("shop")  String shop, Model model) {
        model.addAttribute("TableDistributionPhone", simDistributionServise.tableShopRemanis(shop));
        return "SimDistributionsNew::TableDistributionPhone";

    }
    @ResponseBody
    @RequestMapping(value = "tableUpDistributionSim/{shop}/{models}/{quantity}/{brend}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> tableUpDistributionButton(@PathVariable("shop")  String shop, @PathVariable("models")  String models,@PathVariable("quantity")  String quantity,@PathVariable("brend")  String brend) {

        return simDistributionServise.tableUpDistributionSim(shop,models.replaceAll("_","/"),quantity,brend);
    }
    @GetMapping("/exselDistributionSim")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionSim.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionSim.exportPrisePromoFile(simDistributionServise.exselDistributionSim());

        IOUtils.copy(inputStream, response.getOutputStream());

    }

}
