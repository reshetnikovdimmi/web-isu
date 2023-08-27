package com.myisu_1.isu.service;

import com.myisu_1.isu.models.RTK.MatrixRTK;
import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.Authorization_tt;
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

@Service
public class SimDistributionServise {
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
    Map<String,Map<String,Map<String,String>>> shopSimRTK;
    List<Authorization_tt> authorization;
    Map<String,Map<String,Map<String,Map<String,String>>>> remanSaleSimShop;
    public Map<String,Map<String,String>> needSim() {
        remanSaleSimShop = new TreeMap<>();
        Map<String,Map<String,String>> shopSimRTKName = new TreeMap<>();
        Map<String,String> shopSimRTKPlan;

        authorization = (List<Authorization_tt>) authorization_ttRepositoriy.findAll();
            for (SimAndRtkTable simAndRtkTable : rtkTableRepositoriy.findAll()) {
                shopSimRTKPlan = new TreeMap<>();
                shopSimRTKPlan.put("view",simAndRtkTable.getView());

                Integer sale6 = saleSimModemRepository6m.getSale6Sim(simAndRtkTable.getNameRainbow());
                Integer sale1 = saleSimModemRepository1m.getSale1Sim(simAndRtkTable.getNameRainbow());
                Integer remanis = remanisSimRepository.getSumRemanisSim(simAndRtkTable.getNameRainbow());
                Integer remanisCash = remanisSimRepository.getRemanisSimShop(simAndRtkTable.getNameRainbow(),authorization.get(0).getName());


                if (sale6!=null){
                    shopSimRTKPlan.put("sale6",String.valueOf(sale6/6));
                }else{
                    shopSimRTKPlan.put("sale6", null);
                }
                if (sale1!=null){
                    shopSimRTKPlan.put("sale1",String.valueOf(sale1));
                }else{
                    shopSimRTKPlan.put("sale1", null);
                }
                if (remanis!=null){
                    shopSimRTKPlan.put("remanis", String.valueOf(remanis));
                }else{
                    shopSimRTKPlan.put("remanis", null);
                }


                if (remanisCash!=null){
                    shopSimRTKPlan.put("remanisCash", String.valueOf(remanisCash));

                }else{
                    shopSimRTKPlan.put("remanisCash", null);
                }
                if (remanisCash==null){
                    remanisCash=0;
                }

                Integer plan = plan(simAndRtkTable.getNameRainbow());
                plan = plan+plan/3-remanisCash;

                while ((plan % 50) != 0) {
                    plan++;
                }
                shopSimRTKPlan.put("plan", String.valueOf(plan));


                shopSimRTKName.put(simAndRtkTable.getNameRainbow(),shopSimRTKPlan);
            }

        return shopSimRTKName;
    }

    private Integer plan(String nameRainbow) {
        Integer plan = 0;

            Integer plans = shopPlanSimRepository.getPlanShopSim(authorization_ttRepositoriy.getShopList(),nameRainbow);
            Integer sale6 = saleSimModemRepository6m.getSale6AllShopSim(authorization_ttRepositoriy.getShopList(),nameRainbow);
            Integer sale1 = saleSimModemRepository1m.getSale1AllShopSim(authorization_ttRepositoriy.getShopList(),nameRainbow);

            if (sale6==null){
                sale6=0;
            }
        if (sale1==null){
            sale1=0;
        }

            if(plans!=null){
                plan += plans;
            }else {
                plan += 0;
            }
        plan = Math.max(Math.max(plan, sale6/6),sale1);




        return plan;
    }

    public Map<String,Map<String,String>> nameSimShop(String nameSim, String view) {

        Map<String,Map<String,String>> ddd = new TreeMap<>();


        for (Authorization_tt shop : authorization)    {
            Map<String,String> dddd = new TreeMap<>();
            if (shop.getSimT2().equals(view)){

                Integer sale6 = saleSimModemRepository6m.getSale6SimShop(nameSim,shop.getName());
                if(sale6==null){
                    sale6 =0;
                }

                dddd.put("remanis", String.valueOf(remanisSimRepository.getRemanisSimShop(nameSim,shop.getName())));
                dddd.put("sale1", String.valueOf(saleSimModemRepository1m.getSale1SimShop(nameSim,shop.getName())));
                dddd.put("sale6",String.valueOf(sale6/6));

                ddd.put(shop.getName(),dddd);
            }else if (shop.getSimMts().equals(view)){
                Integer sale6 = saleSimModemRepository6m.getSale6SimShop(nameSim,shop.getSimMts());
                if(sale6==null){
                    sale6 =0;
                }

                dddd.put("remanis", String.valueOf(remanisSimRepository.getRemanisSimShop(nameSim,shop.getName())));
                dddd.put("sale1", String.valueOf(saleSimModemRepository1m.getSale1SimShop(nameSim,shop.getName())));
                dddd.put("sale6",String.valueOf(sale6/6));

                ddd.put(shop.getName(),dddd);
            }


        }


        return ddd;
    }

    public Object remanSimCash(String nameSim) {

        //remanSaleSimShop(authorization.get(0).getName()).get(nameSim);
        return remanSaleSimShop(authorization.get(0).getName()).get(rtkTableRepositoriy.getDistributionModelS(nameSim));
    }

    public Map<String,Map<String, Map<String, String>>> remanSaleSimShop(String shop) {
        System.out.println(shop);
        if(remanSaleSimShop.containsKey(shop)){
            return remanSaleSimShop.get(shop);
        }else {


            List<String> distributionModel = rtkTableRepositoriy.getDistributionModelDISTINCT();

            Map<String, Map<String, Map<String, String>>> categorySim = new TreeMap<>();

            for (String distrModel : distributionModel) {

                Map<String, Map<String, String>> sim = new TreeMap<>();

                for (String nameRainbow : rtkTableRepositoriy.getNameRainbow(distrModel)) {
                    String remanisCash;
                    String totalRemanisCash;
                    if (!remanSaleSimShop.containsKey(authorization.get(0).getName())) {
                        remanisCash = String.valueOf(remanisSimRepository.getRemanisSimShop(nameRainbow, authorization.get(0).getName()));
                        totalRemanisCash = String.valueOf(remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(distrModel), authorization.get(0).getName()));
                    } else {
                        remanisCash = remanSaleSimShop.get(authorization.get(0).getName()).get(distrModel).get(nameRainbow).get("remanisCash");
                        totalRemanisCash = remanSaleSimShop.get(authorization.get(0).getName()).get(distrModel).get("total").get("totalRemanisCash");
                    }

                    Map<String, String> simIndicators = new TreeMap<>();
                    simIndicators.put("view", rtkTableRepositoriy.findByNameRainbow(nameRainbow).getView());
                    simIndicators.put("sale1", String.valueOf(saleSimModemRepository1m.getSale1SimShop(nameRainbow, shop)));
                    simIndicators.put("sale6", String.valueOf(saleSimModemRepository6m.getSale6SimShop(nameRainbow, shop)));
                    simIndicators.put("remanis", String.valueOf(remanisSimRepository.getRemanisSimShop(nameRainbow, shop)));
                    simIndicators.put("remanisCash", remanisCash);
                    simIndicators.put("order", "0");

                    sim.put(nameRainbow, simIndicators);

                    simIndicators = new TreeMap<>();
                    simIndicators.put("view", rtkTableRepositoriy.findByNameRainbow(nameRainbow).getView());
                    simIndicators.put("totalRemanis", String.valueOf(remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalSale1", String.valueOf(saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalSale6", String.valueOf(saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalRemanisCash", totalRemanisCash);
                    simIndicators.put("orderCash", "0");
                    sim.put("total", simIndicators);

                }
                categorySim.put(distrModel, sim);
            }
            remanSaleSimShop.put(shop, categorySim);

            return remanSaleSimShop.get(shop);
        }
    }

    public Object updateRemanisCash(String grop) {


        return remanSaleSimShop(authorization.get(0).getName()).get(grop);
    }

    public Map<String, Map<String, Map<String, String>>> tableUpDistributionSim(String shop, String nameRainbow, String quantity, String brend) {
        System.out.println(shop+"--"+nameRainbow+"--"+quantity+"--"+brend);

        String orderResult = String.valueOf(Integer.parseInt(remanSaleSimShop.get(shop).get(brend).get("total").get("orderCash")) + Integer.parseInt(quantity));
        remanSaleSimShop.get(shop).get(brend).get("total").replace("orderCash", orderResult);

        String remCash = String.valueOf(Integer.parseInt(remanSaleSimShop.get(shop).get(brend).get("total").get("totalRemanisCash")) - Integer.parseInt(quantity));
        remanSaleSimShop.get(shop).get(brend).get("total").replace("totalRemanisCash", remCash);

        String sumRemCash = String.valueOf(Integer.parseInt(remanSaleSimShop.get(authorization.get(0).getName()).get(brend).get("total").get("totalRemanisCash")) - Integer.parseInt(quantity));
        remanSaleSimShop.get(authorization.get(0).getName()).get(brend).get("total").replace("totalRemanisCash", sumRemCash);

        String rem = String.valueOf(Integer.parseInt(remanSaleSimShop.get(shop).get(brend).get(nameRainbow).get("remanisCash")) - Integer.parseInt(quantity));

        for (Map.Entry entry : remanSaleSimShop.entrySet()) {
            remanSaleSimShop.get(entry.getKey()).get(brend).get(nameRainbow).replace("remanisCash", rem);
            remanSaleSimShop.get(entry.getKey()).get(brend).get("total").replace("totalRemanisCash", remCash);
        }

        remanSaleSimShop.get(authorization.get(0).getName()).get(brend).get(nameRainbow).replace("remanisCash", rem);
        remanSaleSimShop.get(authorization.get(0).getName()).get(brend).get("total").replace("totalRemanisCash", remCash);
        remanSaleSimShop.get(shop).get(brend).get(nameRainbow).replace("order", quantity);

        return remanSaleSimShop.get(shop);
    }

    public Map<String, Map<String, Map<String, Map<String, String>>>> exselDistributionSim() {
        System.out.println(remanSaleSimShop);
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
