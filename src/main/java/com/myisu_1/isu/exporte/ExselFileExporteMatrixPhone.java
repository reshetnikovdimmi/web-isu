package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExselFileExporteMatrixPhone {
    public  static ByteArrayInputStream exportPrisePromoFile(List<Phone_Smart> phoneSmarts ){

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetNomenclatureReference = workbook.createSheet("Справочник номенклатуры");
            Row nomenclatureReferenceRow = sheetNomenclatureReference.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(0);
            nomenclatureReferenceRowCell.setCellValue("Модель распределения");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(1);
            nomenclatureReferenceRowCell.setCellValue("Модель");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(2);
            nomenclatureReferenceRowCell.setCellValue("Наименование");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(3);
            nomenclatureReferenceRowCell.setCellValue("Y_name");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            nomenclatureReferenceRowCell = nomenclatureReferenceRow.createCell(4);
            nomenclatureReferenceRowCell.setCellValue("Марка");
            nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

            for (int i = 0; i<phoneSmarts.size();i++){

                        Row dataRow = sheetNomenclatureReference.createRow(i+1);
                        dataRow.createCell(0).setCellValue(phoneSmarts.get(i).getMatrix_T2());
                        dataRow.createCell(1).setCellValue(phoneSmarts.get(i).getBrend());
                        dataRow.createCell(2).setCellValue(phoneSmarts.get(i).getModel());
                        dataRow.createCell(3).setCellValue(phoneSmarts.get(i).getModel_GB());
                        dataRow.createCell(4).setCellValue(phoneSmarts.get(i).getPhone());
            }

            sheetNomenclatureReference.autoSizeColumn(0);
            sheetNomenclatureReference.autoSizeColumn(1);
            sheetNomenclatureReference.autoSizeColumn(2);
            sheetNomenclatureReference.autoSizeColumn(3);
            sheetNomenclatureReference.autoSizeColumn(4);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
