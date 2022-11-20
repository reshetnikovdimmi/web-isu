package com.myisu_1.isu.service;

import com.myisu_1.isu.models.Phone.MatrixT2;
import com.myisu_1.isu.repo.MatrixT2Repository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class MatrixT2Servise {
    @Autowired
    private MatrixT2Repository matrixT2Repository;

    public List<MatrixT2> matrixT2table() {

        return matrixT2Repository.findAll();
    }

    public Iterable<MatrixT2> matrixT2Del(String distributionModel) {

        matrixT2Repository.deleteByDistributionModel(distributionModel.replaceAll("_", "/"));

        return matrixT2Repository.findAll();
    }

    public Object matrixT2Update(List<MatrixT2> sim) {
        List<MatrixT2> matrixT2List = matrixT2Repository.findAll();
        for (int i = 0; i < sim.size(); i++) {
            for (int j = 0; j < matrixT2List.size(); j++) {
                if (sim.get(i).getDistributionModel().equals(matrixT2List.get(j).getDistributionModel()) && sim.get(i).getCluster().equals(matrixT2List.get(j).getCluster())) {
                    matrixT2Repository.deleteById(matrixT2List.get(j).getId());

                }


            }
            AddMatrixT2ListNew(sim.get(i).getDistributionModel(), sim.get(i).getCluster(), sim.get(i).getQuantity());
        }

        return matrixT2Repository.findAll();
    }

    private void AddMatrixT2ListNew(String distributionModel, String cluster, String quantity) {
        List<MatrixT2> matrixT2ListNew = new ArrayList<>();
        matrixT2ListNew.add(new MatrixT2(distributionModel, cluster, quantity));
        matrixT2Repository.saveAll(matrixT2ListNew);

    }


    public String matrixT2Import(MultipartFile reapExcelDataFile) throws IOException {

        long start = System.currentTimeMillis();
        List<MatrixT2> matrixT2List = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
          matrixT2Repository.deleteAll();

        int cell = worksheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            XSSFRow row = worksheet.getRow(i);
            for (int j = 1; j<cell;j++) {
                MatrixT2 matrixT2 = new MatrixT2();
                matrixT2.setDistributionModel(row.getCell(0).getStringCellValue());
                matrixT2.setCluster(String.format("%.0f",worksheet.getRow(0).getCell(j).getNumericCellValue()));
                matrixT2.setQuantity(String.format("%.0f",row.getCell(j).getNumericCellValue()));
                matrixT2List.add(matrixT2);
            }

        }
        matrixT2Repository.saveAll(matrixT2List);
        long timeWorkCode = System.currentTimeMillis() - start;
        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        return df.format(new Date(timeWorkCode));
    }
}
