package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteDistributionPhones;
import com.myisu_1.isu.models.Phone.*;
import com.myisu_1.isu.service.PhoneServise;
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

@Controller
public class DistributionOfPhonesController {
    @Autowired
    private PhoneServise phoneServise;

    @GetMapping("/distributionOFphones")
    public String home(Model model) {

        model.addAttribute("DistributionModelPhone",phoneServise.distributionModel());

        return "distributionOFphones";
    }

    @RequestMapping(value = "/RemanisPhoneSach/{matrixT2}", method = RequestMethod.GET)

    private String remanisPhoneSach(@PathVariable("matrixT2") String matrixT2, Model model) {

        model.addAttribute("RemanisPhoneSach", phoneServise.remanisPhoneSach(matrixT2.replaceAll("_","/")));

        return "distributionOFphones::RemanisPhoneSach";

    }
    @RequestMapping(value = "/UpdateRemanisSaleMatrixT2Shop/{matrixT2}", method = RequestMethod.GET)

    private String updateRemanisSaleMatrixT2Shop(@PathVariable("matrixT2") String matrixT2, Model model) {

        model.addAttribute("RemanisSaleMatrixT2Shop", phoneServise.updateRemanisSaleMatrixT2Shop(matrixT2.replaceAll("_","/")));

        return "distributionOFphones::RemanisSaleMatrixT2Shop";

    }
    @RequestMapping(value = "/UpdateRemanisSaleModelShop/{models}", method = RequestMethod.GET)

    private String updateRemanisSaleModelShop(@PathVariable("models") String models, Model model) {

        model.addAttribute("RemanisSaleModelShop", phoneServise.updateRemanisSaleModelShop(models.replaceAll("_","/")));

        return "distributionOFphones::RemanisSaleModelShop";

    }

    @GetMapping("/CreateRemanisPhonesShopT2")
    private String createRemanisPhoneShopT2(Model model) {

       model.addAttribute("RemanisPhoneShop", phoneServise.remanisPhoneShopT2());
       model.addAttribute("RemanisPhoneShopMuilt", phoneServise.remanisPhoneShopMult());

        return "distributionOFphones::RemanisPhoneShop";

    }
    @GetMapping("/CreateMatrixT2")
    private String createMatrixT2(Model model) {
        model.addAttribute("distributionModel",phoneServise.distributionModelMatrix());
        model.addAttribute("CreateMatrixT2", phoneServise.createMatrixT2());
     //   model.addAttribute("RemanisPhoneShopMuilt", phoneServise.remanisPhoneShopMult());

        return "distributionOFphones::CreateMatrixT2";

    }

    @ResponseBody
    @RequestMapping(value = "TableDistributionPhone/{shop}", method = RequestMethod.GET)
    public Map<String,Map<String, Map<String, Integer>>> createTableDistributionPhone(@PathVariable("shop") String shop) {

        return phoneServise.remanisSaleShop(shop);

    }
    @ResponseBody
    @RequestMapping(value = "tableUpDistriPhone/{shop}/{models}/{quantity}/{brend}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, Integer>>> tableUpDistriPhone(@PathVariable("shop")  String shop, @PathVariable("models")  String models,@PathVariable("quantity")  String quantity,@PathVariable("brend")  String brend) {
System.out.println(models+"--"+brend);
        return phoneServise.tableUpDistriPhone(shop,models.replaceAll("_","/"),quantity,brend.replaceAll("_","/"));
    }
    @GetMapping("/exselDistributionPhones")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionPhones.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionPhones.exportPrisePromoFile((List<DistributionPhone>) phoneServise.distributionPhoneList());

        IOUtils.copy(inputStream, response.getOutputStream());


    }
}

