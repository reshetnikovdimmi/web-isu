package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Barcode.DocUnf;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExselFileExporteDocUnf {
    public static ByteArrayInputStream exportDocUnfFile(List<DocUnf> docUnf) {
        int count = 0;

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetStartPromo = workbook.createSheet("Doc");
            Row startPromoRow = sheetStartPromo.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell startPromoRowCell = startPromoRow.createCell(0);
            startPromoRowCell.setCellValue("Номенклатура");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(1);
            startPromoRowCell.setCellValue("Характеристика");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(2);
            startPromoRowCell.setCellValue("Количество");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(3);
            startPromoRowCell.setCellValue("Цена");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            for (int i = 0; i < docUnf.size(); i++) {

                Row dataRowEnd = sheetStartPromo.createRow(i + 1);
                dataRowEnd.createCell(0).setCellValue(docUnf.get(i).getNomenclatures());
                dataRowEnd.createCell(1).setCellValue(docUnf.get(i).getBarcode());
                dataRowEnd.createCell(2).setCellValue(docUnf.get(i).getQuantity());
                dataRowEnd.createCell(3).setCellValue(docUnf.get(i).getPrice()==null?0:docUnf.get(i).getPrice());

            }

            sheetStartPromo.autoSizeColumn(0);
            sheetStartPromo.autoSizeColumn(1);
            sheetStartPromo.autoSizeColumn(2);
            sheetStartPromo.autoSizeColumn(3);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
