package com.myisu_1.isu.exporte;

import com.myisu_1.isu.models.Phone.DistributionPhone;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExselFileExporteDistributionPhones {
    public static ByteArrayInputStream exportPrisePromoFile(Map<String, Map<String, Map<String, Map<String, Integer>>>> exselDistributionButto) {
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


            distingCell.add("Модель распределения");
            distingCell.addAll(exselDistributionButto.keySet());
            for (Map.Entry entry: exselDistributionButto.get(exselDistributionButto.keySet().stream().findFirst().get()).entrySet()) {
                Map<String, Map<String, String>> ppp = (Map<String, Map<String, String>>) entry.getValue();
                for (Map.Entry entry1: ppp.entrySet()) {

                    distingRow.add((String) entry1.getKey());

                }

            }



            Cell nomenclatureReferenceRowCell;
            for (int i = 0; i < distingCell.size(); i++) {

                nomenclatureReferenceRowCell = nameShopRow.createCell(i);
                nomenclatureReferenceRowCell.setCellValue(distingCell.get(i));
                nomenclatureReferenceRowCell.setCellStyle(headlerCellStyle);

                sheetStartPromo.autoSizeColumn(i);

            }
            int cou = 0;
            for (int i = 0; i<distingRow.size();i++){
                Row dataRow = null;

                // dataRow.createCell(0).setCellValue(distingRow.get(i));

                for (int j=1;j<distingCell.size();j++){
                    for (Map.Entry entry: exselDistributionButto.get(distingCell.get(j)).entrySet()) {
                        Map<String, Map<String, Integer>> ppp = (Map<String, Map<String, Integer>>) entry.getValue();
                        if(ppp.get(distingRow.get(i)) !=null && ppp.get(distingRow.get(i)).get("order")!=null && j==1 && ppp.get(distingRow.get(i)).get("order") > 0){
                            cou++;
                            dataRow = sheetStartPromo.createRow(cou+1);
                            dataRow.createCell(j-1).setCellValue(distingRow.get(i));
                            dataRow.createCell(j).setCellValue(ppp.get(distingRow.get(i)).get("remanisCash"));
                        }

                        if(ppp.get(distingRow.get(i)) !=null && ppp.get(distingRow.get(i)).get("order")!=null &&  ppp.get(distingRow.get(i)).get("order") > 0){
                            //  System.out.println(distingRow.get(i) +"--"+ ppp.get(distingRow.get(i)).get("ЗАКАЗ")+"--"+distingCell.get(j)+"--"+j);
                            if (dataRow==null){
                                cou++;
                                dataRow = sheetStartPromo.createRow(cou+1);
                                dataRow.createCell(0).setCellValue(distingRow.get(i));
                            }

                            dataRow.createCell(j).setCellValue(ppp.get(distingRow.get(i)).get("order"));
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
