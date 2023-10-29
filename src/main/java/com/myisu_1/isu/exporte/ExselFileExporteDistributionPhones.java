package com.myisu_1.isu.exporte;

import com.myisu_1.isu.dto.OrderRecommendations;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExselFileExporteDistributionPhones {
    public static ByteArrayInputStream exportPrisePromoFile(OrderRecommendations exselDistributionButto) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetStartPromo = workbook.createSheet("DistributionPhone");
            Row nameShopRow = sheetStartPromo.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headlerCellStyle.setRotation((short) 90);


            ArrayList<String> listWithDuplicateValues;

            List<String> distingCell = new ArrayList<>();
            List<String> distingRow = new ArrayList<>();
            Set<String> ooo = new HashSet<>();

            distingCell.add("Модель распределения");
            for (OrderRecommendations o : exselDistributionButto.getDistributionPhone()) {
                if (o.getOrder() != null) {
                    ooo.add(o.getShop());
                    for (OrderRecommendations r : o.getAll()) {
                        if (r.getOrder() != null) {
                            distingRow.add(r.getNomenclature());
                        }

                    }
                }

            }

            distingCell.addAll(ooo);


            Cell nomenclatureReferenceRowCell;
            for (int i = 0; i < distingCell.size(); i++) {

                nomenclatureReferenceRowCell = nameShopRow.createCell(i);
                nomenclatureReferenceRowCell.setCellValue(distingCell.get(i));
                nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

                sheetStartPromo.autoSizeColumn(i);

            }
            int cou = 0;
            for (int i = 0; i < distingRow.size(); i++) {
                Row dataRow = sheetStartPromo.createRow(i + 1);


                for (int j = 0; j < distingCell.size(); j++) {

                    dataRow.createCell(0).setCellValue(distingRow.get(i));
                    for (OrderRecommendations o : exselDistributionButto.getDistributionPhone()) {
                        for (OrderRecommendations oo : o.getAll()) {
                            if (o.getShop().equals(distingCell.get(j)) && oo.getNomenclature().equals(distingRow.get(i)) && oo.getOrder() != null) {
                                dataRow.createCell(j).setCellValue(oo.getOrder());
                            }

                        }

                    }

                }
            }
            sheetStartPromo.autoSizeColumn(0);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
