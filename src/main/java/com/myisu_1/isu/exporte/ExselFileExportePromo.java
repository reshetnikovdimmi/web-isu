package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExselFileExportePromo {
    public static ByteArrayInputStream exportPrisePromoFile(List<price_promo> endPromo, List<price_promo> startPromo, List<price_promo> promoExtension1, List<Phone_Smart> phones) {
        int count = 0;
        int couEnd = 0;
        int couExtension = 0;
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetStartPromo = workbook.createSheet("Start_Promo");
            Sheet sheetEndPromo = workbook.createSheet("End_Promo");
            Sheet extensionPromo = workbook.createSheet("Extension_Promo");

            Row startPromoRow = sheetStartPromo.createRow(0);
            Row endPromoRowPromoRow = sheetEndPromo.createRow(0);
            Row extensionPromoRowPromoRow = extensionPromo.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell startPromoRowCell = startPromoRow.createCell(0);
            startPromoRowCell.setCellValue("Наименование");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            Cell endPromoRowPromoCell = endPromoRowPromoRow.createCell(0);
            endPromoRowPromoCell.setCellValue("Наименование");
            endPromoRowPromoCell.setCellStyle(headlerCellStyle);

            Cell extensionPromoRowCell = extensionPromoRowPromoRow.createCell(0);
            extensionPromoRowCell.setCellValue("Наименование");
            extensionPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(1);
            startPromoRowCell.setCellValue("Старая цена");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            extensionPromoRowCell = extensionPromoRowPromoRow.createCell(1);
            extensionPromoRowCell.setCellValue("Старая цена");
            extensionPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(2);
            startPromoRowCell.setCellValue("Новая цена");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            extensionPromoRowCell = extensionPromoRowPromoRow.createCell(2);
            extensionPromoRowCell.setCellValue("Новая цена");
            extensionPromoRowCell.setCellStyle(headlerCellStyle);

            endPromoRowPromoCell = endPromoRowPromoRow.createCell(1);
            endPromoRowPromoCell.setCellValue("Новая цена");
            endPromoRowPromoCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(3);
            startPromoRowCell.setCellValue("До...");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            extensionPromoRowCell = extensionPromoRowPromoRow.createCell(3);
            extensionPromoRowCell.setCellValue("До...");
            extensionPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(4);
            startPromoRowCell.setCellValue("Примечание");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            extensionPromoRowCell = extensionPromoRowPromoRow.createCell(4);
            extensionPromoRowCell.setCellValue("Примечание");
            extensionPromoRowCell.setCellStyle(headlerCellStyle);

            endPromoRowPromoCell = endPromoRowPromoRow.createCell(2);
            endPromoRowPromoCell.setCellValue("Примечание");
            endPromoRowPromoCell.setCellStyle(headlerCellStyle);

            for (int i = 0; i<startPromo.size();i++){
                for (int j = 0; j<phones.size();j++) {
                    if(startPromo.get(i).getModels().equals(phones.get(j).getModel_GB())){
                        count++;
                        Row dataRow = sheetStartPromo.createRow(count);
                        dataRow.createCell(0).setCellValue(phones.get(j).getModel());
                        dataRow.createCell(1).setCellValue(startPromo.get(i).getPrice());
                        dataRow.createCell(2).setCellValue(startPromo.get(i).getPrice_promo());
                        dataRow.createCell(3).setCellValue(startPromo.get(i).getEndPromo());
                        dataRow.createCell(4).setCellValue("Промо");

                    }
                }
            }
            for (int i = 0; i<promoExtension1.size();i++){
                for (int j = 0; j<phones.size();j++) {
                    if(promoExtension1.get(i).getModels().equals(phones.get(j).getModel_GB())){
                        couExtension++;
                        Row dataRowExtension = extensionPromo.createRow(couExtension);
                        dataRowExtension.createCell(0).setCellValue(phones.get(j).getModel());
                        dataRowExtension.createCell(1).setCellValue(promoExtension1.get(i).getPrice());
                        dataRowExtension.createCell(2).setCellValue(promoExtension1.get(i).getPrice_promo());
                        dataRowExtension.createCell(3).setCellValue(promoExtension1.get(i).getEndPromo());
                        dataRowExtension.createCell(4).setCellValue("Продление промо");

                    }
                }
            }
            for (int i = 0; i<endPromo.size();i++){
                for (int j = 0; j<phones.size();j++) {
                    if(endPromo.get(i).getModels().equals(phones.get(j).getModel_GB())){
                        couEnd++;
                        Row dataRowEnd = sheetEndPromo.createRow(couEnd);
                        dataRowEnd.createCell(0).setCellValue(phones.get(j).getModel());
                        dataRowEnd.createCell(1).setCellValue(endPromo.get(i).getPrice());
                        dataRowEnd.createCell(2).setCellValue("Конец промо");

                    }
                }
            }

            sheetStartPromo.autoSizeColumn(0);
            sheetStartPromo.autoSizeColumn(1);
            sheetStartPromo.autoSizeColumn(2);
            sheetStartPromo.autoSizeColumn(3);
            sheetStartPromo.autoSizeColumn(4);

            extensionPromo.autoSizeColumn(0);
            extensionPromo.autoSizeColumn(1);
            extensionPromo.autoSizeColumn(2);
            extensionPromo.autoSizeColumn(3);
            extensionPromo.autoSizeColumn(4);

            sheetEndPromo.autoSizeColumn(0);
            sheetEndPromo.autoSizeColumn(1);
            sheetEndPromo.autoSizeColumn(2);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
