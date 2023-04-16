package com.myisu_1.isu.service;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import com.myisu_1.isu.models.accessories.AccessoriesSale1;
import com.myisu_1.isu.models.accessories.AccessoriesSale6;
import com.myisu_1.isu.models.accessories.RemanisAccessories;
import com.myisu_1.isu.repo.AccessoriesSale1Repositoriy;
import com.myisu_1.isu.repo.AccessoriesSale6Repositoriy;
import com.myisu_1.isu.repo.RemanisAccessoriesRepositoriy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettingAccessoriesServise {
    @Autowired
    private RemanisAccessoriesRepositoriy remanisAccessoriesRepositoriy;
    @Autowired
    private AccessoriesSale1Repositoriy accessoriesSale1Repositoriy;
    @Autowired
    private AccessoriesSale6Repositoriy accessoriesSale6Repositoriy;
    public void loadAccessories(MultipartFile accessories) throws IOException {
        List<RemanisAccessories> remanisAccessoriesArrayList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(accessories.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            RemanisAccessories remanisAccessories = new RemanisAccessories();
            XSSFRow row = worksheet.getRow(i);
            remanisAccessories.setShop(row.getCell(0).getStringCellValue());
            remanisAccessories.setNameAccessories(row.getCell(1).getStringCellValue());
            remanisAccessories.setRemainsAccessories((int) row.getCell(2).getNumericCellValue());
            remanisAccessoriesArrayList.add(remanisAccessories);
        }
        remanisAccessoriesRepositoriy.deleteAll();
        remanisAccessoriesRepositoriy.saveAll(remanisAccessoriesArrayList);
    }

    public void loadAccessoriesSale1(MultipartFile accessoriesSale1) throws IOException {
        List<AccessoriesSale1> accessoriesSale1List = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(accessoriesSale1.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            AccessoriesSale1 accessoriesSale11 = new AccessoriesSale1();
            XSSFRow row = worksheet.getRow(i);
            accessoriesSale11.setNameShop(row.getCell(0).getStringCellValue());
            accessoriesSale11.setNameAccessories(row.getCell(1).getStringCellValue());
            accessoriesSale11.setSaleAccessories((int) row.getCell(2).getNumericCellValue());
            accessoriesSale1List.add(accessoriesSale11);
        }
        accessoriesSale1Repositoriy.deleteAll();
        accessoriesSale1Repositoriy.saveAll(accessoriesSale1List);
    }

    public void loadAccessoriesSale6(MultipartFile accessoriesSale6) throws IOException {
        List<AccessoriesSale6> accessoriesSale6List = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(accessoriesSale6.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            AccessoriesSale6 accessoriesSale66 = new AccessoriesSale6();
            XSSFRow row = worksheet.getRow(i);
            accessoriesSale66.setNameShop(row.getCell(0).getStringCellValue());
            accessoriesSale66.setNameAccessories(row.getCell(1).getStringCellValue());
            accessoriesSale66.setSaleAccessories((int) row.getCell(2).getNumericCellValue());
            accessoriesSale6List.add(accessoriesSale66);
        }
        accessoriesSale6Repositoriy.deleteAll();
        accessoriesSale6Repositoriy.saveAll(accessoriesSale6List);
    }
}
