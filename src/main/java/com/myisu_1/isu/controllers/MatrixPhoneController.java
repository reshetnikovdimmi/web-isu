package com.myisu_1.isu.controllers;

import com.myisu_1.isu.exporte.ExselFileExporteMatrixPhone;
import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.repo.*;
import com.myisu_1.isu.service.MatrixPhoneServise;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@Controller
public class MatrixPhoneController {
    @Autowired
    private MatrixPhoneServise matrixPhoneServise;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private PromoRepositoriy promoRepositoriy;
    @Autowired
    private SalesRepositoriy salesRepositoriy;
    @Autowired
    private MarvelClassifierRepositoriy marvelClassifierRepositoriy;
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @GetMapping("/MatrixPhone")
    public String MatrixPhone(Model model) {
        model.addAttribute("Phone", phoneRepositoriy.findAll());
        return "MatrixPhone";
    }
    @ResponseBody
    @RequestMapping(value = "update_MatrixPhone/{id}", method = RequestMethod.GET)
    public Optional<Phone_Smart> update(@PathVariable("id") int id) {
        return phoneRepositoriy.findById(id);
    }
    @PostMapping("/add_MatrixPhone")
    public String add_phone(@RequestParam int IDupdateMatrixPhone,
                            @RequestParam String Matrix_T2,
                            @RequestParam String Brend,
                            @RequestParam String Model,
                            @RequestParam String Model_GB,
                            @RequestParam String Phone,

                            Model model) {

        if (IDupdateMatrixPhone != 0) {
            String modelGb = phoneRepositoriy.findById(IDupdateMatrixPhone).get().getModel_GB();
            String models = phoneRepositoriy.findById(IDupdateMatrixPhone).get().getModel();
            String brend = phoneRepositoriy.findById(IDupdateMatrixPhone).get().getBrend();
            if(!Model_GB.equals(modelGb)){
                promoRepositoriy.updateModelsPricePromo(Model_GB, modelGb);

            }
            if(!Model.equals(models)){
                salesRepositoriy.updatModelSale(Model, models);
                marvelClassifierRepositoriy.updatRainbowNomenclature(Model, models);
            }
            if(!Brend.equals(brend)){
                promoRepositoriy.updateBrendPricePromo(Brend, brend);
                clothingMatchingTableRepositoriy.updateBrend(Brend, brend);
            }
            phoneRepositoriy.save((new Phone_Smart(IDupdateMatrixPhone, Matrix_T2, Brend, Model, Model_GB, Phone)));
            phoneRepositoriy.updateModelsGbPhoneSmart(Model_GB, modelGb);
            phoneRepositoriy.updateBrendPhoneSmart(Brend, brend);

        } else {
            phoneRepositoriy.save((new Phone_Smart(Matrix_T2, Brend, Model, Model_GB, Phone)));
        }
        model.addAttribute("Phone", phoneRepositoriy.findAll());
        return "MatrixPhone";
    }

    @RequestMapping(value="/delet_MatrixPhone/{id}", method=RequestMethod.GET)
    public String delet(@PathVariable int id, Model model) {
        phoneRepositoriy.deleteById(id);
        model.addAttribute("Phone", phoneRepositoriy.findAll());
        return "MatrixPhone::MatrixPhones";
    }




    @GetMapping("/exselExportMatrixPhone")
    public void downloadExselFile(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=MatrixAO.xlsx");
        ByteArrayInputStream inputStream = ExselFileExporteMatrixPhone.exportPrisePromoFile(matrixPhoneServise.loadMatrixPhone());
        IOUtils.copy(inputStream, response.getOutputStream());


    }
    @PostMapping("/matrixPhoneImport")
    public String matrixT2Import(@RequestParam("file") MultipartFile matrixPhoneImport, Model model) throws IOException, ParseException {

        model.addAttribute("time", matrixPhoneServise.exselLoadMatrixPhone(matrixPhoneImport));
        model.addAttribute("Phone", phoneRepositoriy.findAll());
        return "MatrixPhone";
    }
}
