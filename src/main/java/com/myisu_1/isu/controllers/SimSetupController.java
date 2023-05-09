package com.myisu_1.isu.controllers;

import com.myisu_1.isu.models.SIM.*;
import com.myisu_1.isu.repo.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
    private SaleSimModemRepository_6m saleSimModemRepository_6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository_1m;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    RemanisSimRarusrepository remanisSimRarusrepository;

    @GetMapping("/SimSetup")
    public String SimSetup(Model model) {
        simAndRtkTables = simAndRtkTableRepositoriy.findAll();
        model.addAttribute("SIM_TELE2", simAndRtkTables);
        return "SimSetup";
    }

    @PostMapping("/add_SIM")
    public String add_phone(@RequestParam int IDupdateSIM,
                            @RequestParam String view,
                            @RequestParam String nameRainbow,
                            @RequestParam String nameSpark,
                            @RequestParam String nameRarus,
                            @RequestParam String distributionModel,
                            @RequestParam String toOrder,
                            Model model) {
        System.out.println(IDupdateSIM);

        if (IDupdateSIM != 0) {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(IDupdateSIM, view.trim(), nameSpark.trim(), nameRarus.trim(),nameRainbow.trim(), distributionModel.trim(), toOrder.trim())));
        } else {
            simAndRtkTableRepositoriy.save((new SimAndRtkTable(view.trim(), nameSpark.trim(), nameRarus.trim(),nameRainbow.trim(), distributionModel.trim(), toOrder.trim())));
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
    public String delet(@RequestParam int ID, Model model) {
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
        saleSimModemRepository_6m.deleteAll();
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {

            SaleSim_6m saleSim_6m = new SaleSim_6m();
            XSSFRow row = worksheet.getRow(i);
            saleSim_6m.setShop(row.getCell(0).getStringCellValue());
            saleSim_6m.setNameSimAndModem(row.getCell(1).getStringCellValue());
            if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                saleSim_6m.setRemainsSimModem((int) row.getCell(2).getNumericCellValue());
            } else if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                saleSim_6m.setRemainsSimModem(0);
            }
            SaleSim_6mList.add(saleSim_6m);
        }
        saleSimModemRepository_6m.saveAll(SaleSim_6mList);

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());

        return "SimSetup";
    }

    @PostMapping("/sales_1")
    public String SimSetupSales_1(@RequestParam("sales_1") MultipartFile sales_1, Model model) throws IOException, ParseException {
        long start = System.currentTimeMillis();
        List<SaleSim_1m> SaleSim_1mList = new ArrayList<SaleSim_1m>();
        XSSFWorkbook workbook = new XSSFWorkbook(sales_1.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        saleSimModemRepository_1m.deleteAll();
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {

            SaleSim_1m saleSim_1m = new SaleSim_1m();
            XSSFRow row = worksheet.getRow(i);

            saleSim_1m.setShop(row.getCell(0).getStringCellValue());
            saleSim_1m.setNameSimAndModem(row.getCell(1).getStringCellValue());
            if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                saleSim_1m.setRemainsSimModem((int) row.getCell(2).getNumericCellValue());
                
            } else if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                saleSim_1m.setRemainsSimModem(Integer.parseInt(row.getCell(2).getStringCellValue()));

            }
            SaleSim_1mList.add(saleSim_1m);
        }
        saleSimModemRepository_1m.saveAll(SaleSim_1mList);

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());

        return "SimSetup";
    }

    @PostMapping("/remainsSIM")
    public String remainsSIM(@RequestParam("remainsSIM") MultipartFile remainsSIM, Model model) throws IOException, ParseException {
        long start = System.currentTimeMillis();
        List<RemanisSim> RemanisSimList = new ArrayList<RemanisSim>();
        XSSFWorkbook workbook = new XSSFWorkbook(remainsSIM.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        remanisSimRepository.deleteAll();
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            RemanisSim remanisSim = new RemanisSim();
            XSSFRow row = worksheet.getRow(i);
            System.out.println(i);
            remanisSim.setShop(row.getCell(0).getStringCellValue());
            remanisSim.setNameSimAndModem(row.getCell(1).getStringCellValue());
            if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                remanisSim.setRemainsSimModem((int) row.getCell(2).getNumericCellValue());
            } else if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType.BLANK) {
                remanisSim.setRemainsSimModem(0);
            }
            RemanisSimList.add(remanisSim);
        }
        remanisSimRepository.saveAll(RemanisSimList);

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());

        return "SimSetup";
    }

    @PostMapping("/remainsSimRarus")
    public String remainsSimRarus(@RequestParam("remainsSimRarus") MultipartFile remainsSimRarus, Model model) throws IOException, ParseException {
        long start = System.currentTimeMillis();
        List<RemanisSimRarus> RemanisSimRarusList = new ArrayList<RemanisSimRarus>();
        XSSFWorkbook workbook = new XSSFWorkbook(remainsSimRarus.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        remanisSimRarusrepository.deleteAll();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            RemanisSimRarus remanisSimRarus = new RemanisSimRarus();
            XSSFRow row = worksheet.getRow(i);
            remanisSimRarus.setShop(row.getCell(0).getStringCellValue());

            remanisSimRarus.setNameSimAndModem(row.getCell(1).getStringCellValue());

            remanisSimRarus.setRemainsSimModem((int) row.getCell(2).getNumericCellValue());

            RemanisSimRarusList.add(remanisSimRarus);
        }
        remanisSimRarusrepository.saveAll(RemanisSimRarusList);

        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        model.addAttribute("time", df.format(new Date(timeWorkCode)));
        model.addAttribute("SIM_TELE2", simAndRtkTableRepositoriy.findAll());

        return "SimSetup";
    }
}
