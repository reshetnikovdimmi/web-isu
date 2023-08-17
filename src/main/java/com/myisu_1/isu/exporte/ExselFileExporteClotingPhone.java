package com.myisu_1.isu.exporte;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExselFileExporteClotingPhone {
    public static ByteArrayInputStream exportClotingPhone(List<OrderRecommendations> shopRemanis) {


        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheetDisrtClotingPhone = workbook.createSheet("DisrtClotingPhone");


            Row clotingPhoneRow = sheetDisrtClotingPhone.createRow(0);
            Set<String> keys = new HashSet<>();

            for (OrderRecommendations o:shopRemanis){
                if(o.getAll()!=null){

                    for (OrderRecommendations a:o.getAll()){
                        keys.add(a.getShop());
                    }
                }

            }
            List<String> arrShop = new ArrayList<>(keys);
            arrShop.add(0, "MODEL");
List<OrderRecommendations> oder = new ArrayList<>();
            ArrayList<String> arrModel = new ArrayList<String>();
            for (OrderRecommendations o:shopRemanis){
                if(o.getAll()!=null){

                    for (OrderRecommendations a:o.getAll()){
                        if (a.getOrder()!=null)
                        arrModel.add(a.getNomenclature());
                        oder.add(a);
                    }
                }

            }
            arrModel = (ArrayList) arrModel.stream().distinct().collect(Collectors.toList());




            CellStyle headlerCellStyle = workbook.createCellStyle();
            headlerCellStyle.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
            headlerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headlerCellStyle.setRotation((short) 90);
            for (int i = 0; i < arrShop.size(); i++) {
                Cell startPromoRowCell = clotingPhoneRow.createCell(i);
                startPromoRowCell.setCellValue(arrShop.get(i));
                startPromoRowCell.setCellStyle(headlerCellStyle);
                sheetDisrtClotingPhone.autoSizeColumn(i);
            }
            for (int j = 0; j < arrModel.size(); j++) {

                Row dataRow = sheetDisrtClotingPhone.createRow(j + 1);

                dataRow.createCell(0).setCellValue(arrModel.get(j));
                for (int i = 0; i < arrShop.size(); i++) {
                   if (i > 0) {
                      for (OrderRecommendations d : oder) {
System.out.println(d);
                       if (d.getOrder()!=null && d.getShop().equals(arrShop.get(i))&&d.getNomenclature().equals(arrModel.get(j))) {

                              dataRow.createCell(i).setCellValue(d.getOrder());

                      }

                  }
                   }


                }

            }









            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
