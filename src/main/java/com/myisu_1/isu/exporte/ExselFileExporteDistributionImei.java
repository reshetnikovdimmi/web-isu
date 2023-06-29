package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Suppliers;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExselFileExporteDistributionImei {
    public static ByteArrayInputStream exportPrisePromoFile(List<Suppliers> all) {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetNomenclatureReference = workbook.createSheet("Imei");
            Row nomenclatureReferenceRow = sheetNomenclatureReference.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(0);
            nomenclatureReferenceRowCell.setCellValue("Поставщик");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(1);
            nomenclatureReferenceRowCell.setCellValue("Imei");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);



            for (int i = 0; i<all.size();i++){

                Row dataRow = sheetNomenclatureReference.createRow(i+1);
                dataRow.createCell(0).setCellValue(all.get(i).getSuppliers());
                dataRow.createCell(1).setCellValue(all.get(i).getImei());

            }

            sheetNomenclatureReference.autoSizeColumn(0);
            sheetNomenclatureReference.autoSizeColumn(1);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    }

