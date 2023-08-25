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
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Service
public class BarcodeServise {
    @Autowired
    private BarcodeSparkRepository barcodeSparkRepository;
    @Autowired
    private BarcodeUnfRepository barcodeUnfRepository;
    @Autowired
    private DocUnfRepository docUnfRepository;
    List<DocUnf> docUnfs = new ArrayList<>();

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

        List<DocUnf> docUnfList;
        String br = null;
        List<String> barcode = new ArrayList<>();
        try {
            docUnfList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                DocUnf docUnf = new DocUnf();
                XSSFRow row = worksheet.getRow(i);
                docUnf.setNomenclatures(row.getCell(0).getStringCellValue());
                br = String.valueOf(row.getCell(1).getCellType() == CellType.ERROR ? null : row.getCell(1).getStringCellValue());
                if (br != null) barcode.add(br);
                docUnf.setBarcode(br);
                docUnf.setQuantity(row.getCell(2).getCellType() == CellType.ERROR ? null : row.getCell(2).getNumericCellValue());
                docUnf.setPrice(row.getCell(3).getCellType() == CellType.ERROR ? null : row.getCell(3).getNumericCellValue());
                docUnfList.add(docUnf);
            }
            docUnfRepository.saveAll(docUnfList);
        } catch (ObjectOptimisticLockingFailureException ex) {
            return new ResponseEntity<>("подожди", HttpStatus.BAD_REQUEST);


        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Invalid file format!!", HttpStatus.BAD_REQUEST);

        }
        barcode.addAll(docUnfRepository.shkDocUnfs());
        List<DocUnf> docUnfsNew = docUnfRepository.shkDocUnf(barcode);
        docUnfs.addAll(docUnfsNew);


        Collection<DocUnf> distinctEmps = docUnfs.stream()
                .collect(Collectors.toMap(DocUnf::getBarcode, Function.identity(),
                        (e1, e2) -> e1.getBarcode() != e2.getBarcode() ? e1 : e2))
                .values();
        docUnfs = distinctEmps.stream().collect(toCollection(ArrayList::new));


        return new ResponseEntity<>("Загружено строк" + "  " + docUnfsNew.size() + "  " + "из" + "  " + docUnfList.size()+ "  " + "из книги " + "  " +file.getOriginalFilename(), HttpStatus.OK);
    }

    public List<DocUnf> getDocUnf() {
        List<DocUnf> docUnfsNew = docUnfs;
        docUnfs = new ArrayList<>();
        return docUnfsNew;
    }
}
