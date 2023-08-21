package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Barcode.BarcodeSpark;
import com.myisu_1.isu.models.Barcode.BarcodeUnf;
import com.myisu_1.isu.models.Barcode.DocUnf;
import com.myisu_1.isu.repo.BarcodeSparkRepository;
import com.myisu_1.isu.repo.BarcodeUnfRepository;
import com.myisu_1.isu.repo.DocUnfRepository;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class BarcodeServise {
    @Autowired
    private BarcodeSparkRepository barcodeSparkRepository;
    @Autowired
    private BarcodeUnfRepository barcodeUnfRepository;
    @Autowired
    private DocUnfRepository docUnfRepository;
    public ResponseEntity<String> saveBarcodeSpark(MultipartFile file) {
        long start = System.currentTimeMillis();
        barcodeSparkRepository.deleteAll();
        try {
            List<BarcodeSpark> barcodeSparkList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                BarcodeSpark barcodeSpark = new BarcodeSpark();
                XSSFRow row = worksheet.getRow(i);
                barcodeSpark.setNomenclature(row.getCell(0).getStringCellValue());
                barcodeSpark.setBarcode(String.valueOf(row.getCell(1).getStringCellValue()));
                barcodeSparkList.add(barcodeSpark);
            }
            barcodeSparkRepository.saveAll(barcodeSparkList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid file format!!", HttpStatus.BAD_REQUEST);
        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return new ResponseEntity<>("File uploaded за" + " - " + df.format(new Date(timeWorkCode)), HttpStatus.OK);



    }

    public ResponseEntity<String> saveBarcodeUnf(MultipartFile file) {
        long start = System.currentTimeMillis();
        barcodeUnfRepository.deleteAll();
        try {
            List<BarcodeUnf> barcodeUnfList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                BarcodeUnf barcodeUnf = new BarcodeUnf();
                XSSFRow row = worksheet.getRow(i);
                barcodeUnf.setBarcode(row.getCell(0).getStringCellValue());
                barcodeUnf.setNomenclature(row.getCell(1).getStringCellValue());
                barcodeUnfList.add(barcodeUnf);
            }
            barcodeUnfRepository.saveAll(barcodeUnfList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid file format!!", HttpStatus.BAD_REQUEST);
        }
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return new ResponseEntity<>("File uploaded за" + " - " + df.format(new Date(timeWorkCode)), HttpStatus.OK);


    }

    public ResponseEntity<String> loadDoc(MultipartFile file) {
        docUnfRepository.deleteAll();
        try {
            List<DocUnf> docUnfList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                DocUnf docUnf = new DocUnf();
                XSSFRow row = worksheet.getRow(i);
                docUnf.setNomenclature(row.getCell(0).getStringCellValue());
                docUnf.setBarcode(String.valueOf(row.getCell(1).getCellType()==CellType.ERROR?null:row.getCell(1).getStringCellValue()));
                docUnf.setQuantity(row.getCell(2).getCellType()==CellType.ERROR?null:row.getCell(2).getNumericCellValue());
                docUnf.setPrice(row.getCell(3).getCellType()==CellType.ERROR?null:row.getCell(3).getNumericCellValue());
                docUnfList.add(docUnf);
            }

            docUnfRepository.saveAll(docUnfList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid file format!!", HttpStatus.BAD_REQUEST);
        }
System.out.println(docUnfRepository.findAll());
        return new ResponseEntity<>("File uploaded " , HttpStatus.OK);
    }
}
