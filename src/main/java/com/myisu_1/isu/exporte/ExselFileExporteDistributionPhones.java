package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Phone.DistributionPhone;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExselFileExporteDistributionPhones {
    public static ByteArrayInputStream exportPrisePromoFile(List<DistributionPhone> requirementUpPhone) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheetStartPromo = workbook.createSheet("DistributionPhones");
            Row nameShopRow = sheetStartPromo.createRow(0);

            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headlerCellStyle.setRotation((short) 90);


            ArrayList<String> listWithDuplicateValues;

            List<String> distingCell = new ArrayList<>();
            List<String> distingRow = new ArrayList<>();

            listWithDuplicateValues = new ArrayList<>();
            listWithDuplicateValues.add("Модель распределения");
            for (int i = 0; i < requirementUpPhone.size(); i++) {
                listWithDuplicateValues.add(requirementUpPhone.get(i).getShop());
            }
            distingCell = (ArrayList) listWithDuplicateValues
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());


            listWithDuplicateValues = new ArrayList<>();
            for (int i = 0; i < requirementUpPhone.size(); i++) {
                if (!requirementUpPhone.get(i).isSky() && requirementUpPhone.get(i).getSkyPhone()!=0) {
                    listWithDuplicateValues.add(requirementUpPhone.get(i).getModelPhone());
                }

            }

            distingRow = (ArrayList) listWithDuplicateValues
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());


            Cell nomenclatureReferenceRowCell;
            for (int i = 0; i < distingCell.size(); i++) {

                nomenclatureReferenceRowCell = nameShopRow.createCell(i);
                nomenclatureReferenceRowCell.setCellValue(distingCell.get(i));
                nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

                sheetStartPromo.autoSizeColumn(i);

            }

            for (int i = 0; i<distingRow.size();i++){

                Row dataRow = sheetStartPromo.createRow(i+1);
                dataRow.createCell(0).setCellValue(distingRow.get(i));

                for (int j=1;j<distingCell.size();j++){
                    for (int k=0;k<requirementUpPhone.size();k++){
                        if (distingRow.get(i).equals(requirementUpPhone.get(k).getModelPhone())&& distingCell.get(j).equals(requirementUpPhone.get(k).getShop())&&requirementUpPhone.get(k).getSkyPhone()>0){
                            dataRow.createCell(j).setCellValue(requirementUpPhone.get(k).getSkyPhone());
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
