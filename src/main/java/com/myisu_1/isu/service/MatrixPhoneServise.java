package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class MatrixPhoneServise {
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;

    public List<Phone_Smart> loadMatrixPhone() {
        List<Phone_Smart> phoneSmartList = new ArrayList<>();

        phoneSmartList = phoneRepositoriy.findAll();
        return phoneSmartList;
    }

    public String exselLoadMatrixPhone(MultipartFile matrixPhoneImport) throws IOException {

        long start = System.currentTimeMillis();

        List<Phone_Smart> phoneSmartList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(matrixPhoneImport.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        phoneRepositoriy.deleteAll();


        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            XSSFRow row = worksheet.getRow(i);

            Phone_Smart phone_smart = new Phone_Smart();
            phone_smart.setMatrix_T2(row.getCell(0).getStringCellValue());
            phone_smart.setBrend(row.getCell(1).getStringCellValue());
            phone_smart.setModel(row.getCell(2).getStringCellValue());
            phone_smart.setModel_GB(row.getCell(3).getStringCellValue());
            phone_smart.setPhone(row.getCell(4).getStringCellValue());
            phoneSmartList.add(phone_smart);

        }
        phoneRepositoriy.saveAll(phoneSmartList);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        return df.format(new Date(timeWorkCode));


    }
}
