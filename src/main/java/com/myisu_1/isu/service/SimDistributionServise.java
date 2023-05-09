package com.myisu_1.isu.service;

import com.myisu_1.isu.models.SIM.SimAndRtkTable;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    Map<String,Map<String,Map<String,String>>> shopSimRTK;
    List<authorization_tt> authorization;
    Map<String,Map<String,Map<String,Map<String,String>>>> remanSaleSimShop;
    public Map<String,Map<String,String>> needSim() {
        remanSaleSimShop = new TreeMap<>();
        Map<String,Map<String,String>> shopSimRTKName = new TreeMap<>();
        Map<String,String> shopSimRTKPlan;

        authorization = (List<authorization_tt>) authorization_ttRepositoriy.findAll();
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


        for (authorization_tt shop : authorization)    {
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
                    if (!remanSaleSimShop.containsKey(authorization.get(0).getName())) {
                        remanisCash = String.valueOf(remanisSimRepository.getRemanisSimShop(nameRainbow, authorization.get(0).getName()));
                    } else {
                        remanisCash = remanSaleSimShop.get(authorization.get(0).getName()).get(distrModel).get(nameRainbow).get("remanis");
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
                    simIndicators.put("totalRemanis", String.valueOf(remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalSale1", String.valueOf(saleSimModemRepository1m.getSale1DistrModel(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalSale6", String.valueOf(saleSimModemRepository6m.getSale6DistrModel(rtkTableRepositoriy.getNameRainbow(distrModel), shop)));
                    simIndicators.put("totalRemanisCash", String.valueOf(remanisSimRepository.totalSimRTK(rtkTableRepositoriy.getNameRainbow(distrModel), authorization.get(0).getName())));
                    simIndicators.put("orderCash", "0");
                    sim.put("total", simIndicators);

                }
                categorySim.put(distrModel, sim);
            }
            remanSaleSimShop.put(shop, categorySim);

            return remanSaleSimShop.get(shop);
        }
    }
}
