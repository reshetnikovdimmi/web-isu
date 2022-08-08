package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.MarvelPromo;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.price_promo;
import com.myisu_1.isu.repo.SaleSimModemRepository;
import com.myisu_1.isu.repo.SimAndRtkTableRepositoriy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SimSetupController {
List<SimAndRtkTable> simAndRtkTables;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository saleSimModemRepository;

    @GetMapping("/SimSetup")
    public String SimSetup(Model model) {
        simAndRtkTables = simAndRtkTableRepositoriy.findAll();
        model.addAttribute("SIM_TELE2", simAndRtkTables);
        return "SimSetup";
    }

    @PostMapping("/add_SIM")
    public String add_phone(@RequestParam int IDupdateSIM,
                            @RequestParam String view,
                            @RequestParam String nameSpark,
                            @RequestParam String nameRarus,
                            @RequestParam String distributionModel,
                            @RequestParam String toOrder,
                            Model model) {
        System.out.println(IDupdateSIM);

        if (IDupdateSIM != 0) {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(IDupdateSIM,view,nameSpark,nameRarus,distributionModel,toOrder)));
        }else {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(view,nameSpark,nameRarus,distributionModel,toOrder)));
        }

        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());
        return "SimSetup";
    }

    @ResponseBody
    @RequestMapping(value = "update_SIM/{id}", method = RequestMethod.GET)
    public Optional<SimAndRtkTable> update(@PathVariable("id") int id) {

        return simAndRtkTableRepositoriy.findById(id);
    }
    @PostMapping("/delet_SIM")
    public String delet(@RequestParam int ID,Model model) {
        simAndRtkTableRepositoriy.deleteById(ID);
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());
        return "SimSetup";
    }

    @PostMapping("/sales_6")
    public String SimSetupSales_6(@RequestParam("sales_6") MultipartFile sales_6, Model model) throws IOException, ParseException {
       long start = System.currentTimeMillis();
        List<SaleSim_6m> SaleSim_6mList = new ArrayList<SaleSim_6m>();
        XSSFWorkbook workbook = new XSSFWorkbook(sales_6.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        saleSimModemRepository.deleteAll();
        for(int i=2;i<worksheet.getPhysicalNumberOfRows()-1 ;i++) {
          SaleSim_6m saleSim_6m = new SaleSim_6m();

            XSSFRow row = worksheet.getRow(i);

            saleSim_6m.setShop(row.getCell(0).getStringCellValue());
            saleSim_6m.setNameSimAndModem(row.getCell(1).getStringCellValue());

            if(row.getCell(2).getCellType() == CellType.NUMERIC) {
                System.out.println(row.getCell(2).getNumericCellValue());
                saleSim_6m.setRemainsSimModem((int) row.getCell(2).getNumericCellValue());
            }else if (row.getCell(2) == null ||row.getCell(2).getCellType()==CellType.BLANK){
                System.out.println(0);
                saleSim_6m.setRemainsSimModem(0);
            }
            SaleSim_6mList.add(saleSim_6m);






        }
      saleSimModemRepository.saveAll(SaleSim_6mList);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        // System.out.println(df.format(new Date(timeWorkCode)));

      //  model.addAttribute("time", df.format(new Date(timeWorkCode)));
        // System.out.println(((List<Suppliers>) suppliersRepositoriy.findAll()).size());*/
       model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());

        return "SimSetup";
    }
}
