package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteDistributionAccessories;
import com.myisu_1.isu.exporte.ExselFileExporteDistributionButton;
import com.myisu_1.isu.models.accessories.SettingAccessories;
import com.myisu_1.isu.repo.SettingAccessoriesRepositoriy;
import com.myisu_1.isu.service.AccessoriesServise;
import com.myisu_1.isu.service.PhoneServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class AccessoriesController {

    @Autowired
    private AccessoriesServise accessoriesServise;
    @GetMapping("/Accessories")
    public String home(Model model) {
        model.addAttribute("Accessories", accessoriesServise.AccessoriesCategory());


        return "Accessories";
    }

    @RequestMapping(value = "/AccessoriesCategoryShop/{Grup}", method = RequestMethod.GET)

    private String DelAccessoriesCategory(@PathVariable("Grup") String Grup, Model model) {

        model.addAttribute("AccessoriesCategoryShop", accessoriesServise.accessoriesCategoryShop(Grup.trim()));
        return "Accessories::AccessoriesCategoryShop";
    }
    @ResponseBody
    @RequestMapping(value = "AccessoriesCategoryNomenclatureShop/{shop}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> AccessoriesCategoryNomenclatureShop(@PathVariable("shop") String shop) {

        return accessoriesServise.accessoriesCategoryNomenclatureShop(shop.trim());

    }


    @RequestMapping(value = "/AccessoriesCategoryMaxSale/{grop}", method = RequestMethod.GET)

    private String accessoriesCategoryMaxSale(@PathVariable("grop") String grop, Model model) {

       model.addAttribute("AccessoriesCategoryMaxSale", accessoriesServise.accessoriesCategoryMaxSale(grop.trim()));
        return "Accessories::AccessoriesCategoryMaxSale";

    }

    @RequestMapping(value = "/AccessoriesCategoryCash/{Grup}", method = RequestMethod.GET)

    private String AccessoriesCategoryCash(@PathVariable("Grup") String Grup, Model model) {

        model.addAttribute("graduation", accessoriesServise.accessoriesCategoryCash(Grup.trim()));
        return "Accessories::RemanisCash";
    }
    @ResponseBody
    @RequestMapping(value = "tableUpDistributionShop/{shop}/{models}/{quantity}/{brend}", method = RequestMethod.GET)
    public Map<String, Map<String, Map<String, String>>> tableUpDistributionButton(@PathVariable("shop")  String shop, @PathVariable("models")  String models,@PathVariable("quantity")  String quantity,@PathVariable("brend")  String brend) {

        return accessoriesServise.tableUpDistributionShop(shop,models,quantity,brend);
    }
    @GetMapping("/exselDistributionAccessories")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=DistributionAccessories.xlsx");

        ByteArrayInputStream inputStream = ExselFileExporteDistributionAccessories.exportPrisePromoFile(accessoriesServise.exselDistributionAccessories());

        IOUtils.copy(inputStream, response.getOutputStream());

    }
}
