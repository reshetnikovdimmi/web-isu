package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.price_promo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ExselFileExporte {

    public  static ByteArrayInputStream exportPrisePromoFile(List<price_promo> prisePromo){
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Start_Promo");
            Row row = sheet.createRow(0);
            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
