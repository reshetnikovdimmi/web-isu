package com.myisu_1.isu.service;

import com.myisu_1.isu.dto.OrderRecommendations;
import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.models.Authorization_tt;
import com.myisu_1.isu.models.distribution.AnalysisDistribution;
import com.myisu_1.isu.repo.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimDistributionServise extends AnalysisDistribution {
    @Autowired
    private SimAndRtkTableRepositoriy rtkTableRepositoriy;
    @Autowired
    private SaleSimModemRepository_6m saleSimModemRepository6m;
    @Autowired
    private SaleSimModemRepository_1m saleSimModemRepository1m;
    @Autowired
    private RemanisSimRepository remanisSimRepository;
    @Autowired
    private PostRepositoriy authorization_ttRepositoriy;
    @Autowired
    private ShopPlanSimRepository shopPlanSimRepository;
    @Autowired
    private MatrixRTKRepository matrixRTKRepository;
    @Autowired
    private SimAndRtkTableRepositoriy simAndRtkTableRepositoriy;

    List<Authorization_tt> authorization;
    Map<String,Map<String,Map<String,Map<String,String>>>> remanSaleSimShop;
    public OrderRecommendations remainsCash() {
        or = new OrderRecommendations();
        remainsNomenclature = simAndRtkTableRepositoriy.remainsSim();
        warehouse = authorization_ttRepositoriy.getShopList();
        sale1Nomenclature = simAndRtkTableRepositoriy.getSale1Phone();
        sale6Nomenclature = simAndRtkTableRepositoriy.getSale6Phone();
        phoneSmarts = simAndRtkTableRepositoriy.phoneSmar();
        remainsCashGroup(simAndRtkTableRepositoriy.getGroupViewSim());
        remainsNomenclatureSach(simAndRtkTableRepositoriy.getNameRainbows());
        indicatorsPhoneShopGroup(simAndRtkTableRepositoriy.getDistributionModelDISTINCT(), null);
        distributionPhone(simAndRtkTableRepositoriy.getGroupViewSim());
        return or;
    }



    public OrderRecommendations  nameSimShop() {



        return or;
    }

    public Object remanSimCash(String nameSim) {


        return remanSaleSimShop(authorization.get(0).getName()).get(rtkTableRepositoriy.getDistributionModelS(nameSim));
    }
    public List<OrderRecommendations> tableShopRemanis(String shop) {

        return or.getDistributionPhone().stream().filter(r -> r.getShop().equals(shop)).collect(Collectors.toList());
    }

    public Map<String,Map<String, Map<String, String>>> remanSaleSimShop(String shop) {

            return null;

    }

    public Object updateRemanisCash(String grop) {


        return remanSaleSimShop(authorization.get(0).getName()).get(grop);
    }

    public Map<String, Map<String, Map<String, String>>> tableUpDistributionSim(String shop, String nameRainbow, String quantity, String brend) {


        return null;
    }

    public Map<String, Map<String, Map<String, Map<String, String>>>> exselDistributionSim() {

        return remanSaleSimShop;
    }


        public String loadExelRTK(MultipartFile loadExelRTK) throws IOException {

            long start = System.currentTimeMillis();
            List<MatrixRTK> matrixRTKList = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(loadExelRTK.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);
            matrixRTKRepository.deleteAll();

            int cell = worksheet.getRow(0).getPhysicalNumberOfCells();
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                XSSFRow row = worksheet.getRow(i);
                for (int j = 1; j<cell;j++) {
                    MatrixRTK matrixRTK = new MatrixRTK();

                    matrixRTK.setCluster(row.getCell(0).getStringCellValue());
                    matrixRTK.setDistributionModel(worksheet.getRow(0).getCell(j).getStringCellValue());
                    matrixRTK.setQuantity((int) row.getCell(j).getNumericCellValue());
                    matrixRTKList.add(matrixRTK);
                }

            }
            matrixRTKRepository.saveAll(matrixRTKList);
            long timeWorkCode = System.currentTimeMillis() - start;
            DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
            df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

            return df.format(new Date(timeWorkCode));
        }

    public Object getMatrixRTK() {
        Map<String,Map<String,Integer>> matrixRTK = new TreeMap<>();
        Map<String,Integer> distributionModel;
        List<String> cluster = matrixRTKRepository.getClusterDistinct();
        List<String> model = matrixRTKRepository.getDistributionModelDistinct();

      for (String clusters: cluster){
          distributionModel = new TreeMap<>();
          for (String models: model){
              distributionModel.put(models,matrixRTKRepository.getQuantity(clusters,models));

          }

          matrixRTK.put(clusters,distributionModel);

      }

        return matrixRTK;
    }

    public Object distributionModel() {
        return matrixRTKRepository.getDistributionModelDistinct();
    }
}
