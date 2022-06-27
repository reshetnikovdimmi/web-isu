package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExselFileExporte {

    public  static ByteArrayInputStream exportPrisePromoFile(List<price_promo> prisePromo, List<Phone_Smart> phones, List<price_promo> endpromo){

        int count = 0;

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetStartPromo = workbook.createSheet("Start_Promo");
            Sheet sheetEndPromo = workbook.createSheet("End_Promo");

            Row startPromoRow = sheetStartPromo.createRow(0);
            Row endPromoRowPromoRow = sheetEndPromo.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell startPromoRowCell = startPromoRow.createCell(0);
            startPromoRowCell.setCellValue("Наименование");
            startPromoRowCell.setCellStyle(headlerCellStyle);


            startPromoRowCell = startPromoRow.createCell(1);
            startPromoRowCell.setCellValue("Старая цена");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(2);
            startPromoRowCell.setCellValue("Новая цена");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(3);
            startPromoRowCell.setCellValue("До...");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            startPromoRowCell = startPromoRow.createCell(4);
            startPromoRowCell.setCellValue("Примечание");
            startPromoRowCell.setCellStyle(headlerCellStyle);

            for (int i = 0; i<prisePromo.size();i++){
                for (int j = 0; j<phones.size();j++) {
                  if(prisePromo.get(i).getModels().equals(phones.get(j).getModel_GB())){
                      count++;
                      Row dataRow = sheetStartPromo.createRow(count);
                      dataRow.createCell(0).setCellValue(phones.get(j).getModel());
                      dataRow.createCell(1).setCellValue(prisePromo.get(i).getPrice());
                      dataRow.createCell(2).setCellValue(prisePromo.get(i).getPrice_promo());
                      dataRow.createCell(3).setCellValue(prisePromo.get(i).getEndPromo());
                      dataRow.createCell(4).setCellValue("Промо");

                  }


                }
            }

            sheetStartPromo.autoSizeColumn(0);
            sheetStartPromo.autoSizeColumn(1);
            sheetStartPromo.autoSizeColumn(2);
            sheetStartPromo.autoSizeColumn(3);
            sheetStartPromo.autoSizeColumn(4);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
