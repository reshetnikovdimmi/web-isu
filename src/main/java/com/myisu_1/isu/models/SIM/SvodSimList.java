package com.myisu_1.isu.models.SIM;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SvodSimList extends SimList {


    List<SimSvod> simSvodList1;

    public void parse2() {

        simSvodList = new ArrayList<>();

        for (int i = 0; i < authorization_ttList.size(); i++) {
            for (int j = 0; j < simAndRtkTables.size(); j++) {
                if (authorization_ttList.get(i).getSimT2().equals(simAndRtkTables.get(j).getView())) {
                    addSimSvod(i, j);
                }
                if (authorization_ttList.get(i).getSimMts().equals(simAndRtkTables.get(j).getView())) {
                    addSimSvod(i, j);
                }
                if (authorization_ttList.get(i).getSimMf().equals(simAndRtkTables.get(j).getView())) {
                    addSimSvod(i, j);
                }
            }
        }
    }

    private void addSimSvod(int i, int j) {
        for (int k = 0; k < remanisSimList.size(); k++) {
            if (authorization_ttList.get(i).getShopIskra().equals(remanisSimList.get(k).getShop()) && simAndRtkTables.get(j).getNameSpark().trim().equals(remanisSimList.get(k).getNameSimAndModem())
                    || authorization_ttList.get(i).getName().equals(remanisSimList.get(k).getShop()) && simAndRtkTables.get(j).getNameRainbow().trim().equals(remanisSimList.get(k).getNameSimAndModem())) {
                String shopIskra = authorization_ttList.get(i).getShopIskra();
                String shopRaduga = authorization_ttList.get(i).getName().trim();
                String simIskra = simAndRtkTables.get(j).getNameSpark().trim();
                String simRaduga = simAndRtkTables.get(j).getNameRainbow().trim();
                String sale_6 = sale_6(shopIskra, shopRaduga, simIskra, simRaduga);
                String sale_1 = Sele(shopIskra, shopRaduga, simIskra, simRaduga);

                int plan = plan(sale_6);

                simSvodList.add(new SimSvod(k,
                        idDlan(),
                        remanisSimList.get(k).getNameSimAndModem(),

                        sale_6,
                        sale_1,
                        String.valueOf(remanisSimList.get(k).getRemainsSimModem()),
                        remanisrarus(),
                        plan,
                        planVypol(sale_1, plan),
                        authorization_ttList.get(i).getName(),
                        simAndRtkTables.get(j).getView(),
                        simAndRtkTables.get(j).getToOrder(),
                        distribution(sale_6, sale_1, remanisSimList.get(k).getRemainsSimModem(), plan)));

            }
        }
    }

    private String remanisrarus() {
        return null;
    }

    private String distribution(String sale_6, String sale_1, int nameSimAndModem, int plan) {

        int plans = IntStream.of(Integer.parseInt(sale_6), Integer.parseInt(sale_1), plan).max().getAsInt() - nameSimAndModem;
        plans = rounding(plans);

        return String.valueOf(plans);
    }

    private int rounding(int plans) {
        while ((plans % 5) != 0) {
            plans++;
        }
        return plans;
    }


    public Iterable<SimSvod> parse(String shop, String t2) {
        simSvodList1 = new ArrayList<>();
        for (int i = 0; i < simSvodList.size(); i++) {
            if (shop.equals(simSvodList.get(i).getShop()) && t2.equals(simSvodList.get(i).getView()) && Integer.parseInt(simSvodList.get(i).getDistribution()) > 0) {
                simSvodList1.add(simSvodList.get(i));
            }
        }
        return simSvodList1;
    }

    public Iterable<SimSvod> multiSim(String shop, String t2) {
        simSvodList1 = new ArrayList<>();
        for (int i = 0; i < simSvodList.size(); i++) {
            if (shop.equals(simSvodList.get(i).getNameSim()) && Integer.parseInt(simSvodList.get(i).getDistribution()) > 0) {
                simSvodList1.add(simSvodList.get(i));
            }
        }
        if (simSvodList1.size() == 0) {
            simSvodList1.add(new SimSvod(0,0,shop,"0","0",String.valueOf(0),"0",0,"0",null,"0","0","0"));
        }
        return simSvodList1;
    }

    public Iterable<SimSvod> parse3(String shop, String t2) {
        simSvodList1 = new ArrayList<>();
        for (int i = 0; i < simSvodList.size(); i++) {
            if (shop.equals(simSvodList.get(i).getShop()) && t2.equals(simSvodList.get(i).getView())) {
                simSvodList1.add(simSvodList.get(i));
            }
        }
        return simSvodList1;
    }

    public Iterable<SimSvod> zakazSim(String t2) {
        simSvodList1 = new ArrayList<>();
        for (int i = 0; i < simAndRtkTables.size(); i++) {
            String remanisSkladSIM = remanisSkladSIM(simAndRtkTables.get(i).getNameSpark(),simAndRtkTables.get(i).getNameRainbow());
            String remanisSIM = remanisSIM(simAndRtkTables.get(i).getNameSpark(),simAndRtkTables.get(i).getNameRainbow());
            String averageSalesSIM = averageSalesSIM (simAndRtkTables.get(i).getNameSpark(),simAndRtkTables.get(i).getNameRainbow());
            String recommendedToOrder = recommendedToOrder(remanisSkladSIM,averageSalesSIM);
            SimSvod simSvod = new SimSvod();
            simSvod.setNameSim(simAndRtkTables.get(i).getNameRainbow());
            simSvod.setView(simAndRtkTables.get(i).getView());
            simSvod.setRemanisSkladSIM(remanisSkladSIM);
            simSvod.setRemanisSIM(remanisSIM);
            simSvod.setAverageSalesSIM(averageSalesSIM);
            simSvod.setRecommendedToOrder(recommendedToOrder);
            simSvodList1.add(simSvod);
        }
        return simSvodList1;
    }

    private String recommendedToOrder(String remanisSkladSIM, String averageSalesSIM) {
        int recommendedToOrder =0;
        int rekzakaz = 0;
        if(remanisSkladSIM !=null && Integer.parseInt(averageSalesSIM) > Integer.parseInt(remanisSkladSIM)) {
            recommendedToOrder = Integer.parseInt(remanisSkladSIM);

       // System.out.println(averageSalesSIM+"--"+remanisSkladSIM);
        if(remanisSkladSIM !=null ){
            while (Integer.parseInt(averageSalesSIM) > recommendedToOrder){

                recommendedToOrder = recommendedToOrder + 50;
                rekzakaz = rekzakaz + 50;



            }

        }
    }

        return String.valueOf(rekzakaz);
    }

    private String averageSalesSIM(String nameSpark, String nameRainbow) {
        int averageSalesSIM = 0;
        for (int i=0;i<simSvodList.size();i++){
            if(nameSpark.equals(simSvodList.get(i).getNameSim())||nameRainbow!=null && nameRainbow.equals(simSvodList.get(i).getNameSim())){
                averageSalesSIM = averageSalesSIM + Integer.parseInt(simSvodList.get(i).getSale_6());
            }

        }


        return String.valueOf(averageSalesSIM);
    }

    private String remanisSIM(String nameSpark, String nameRainbow) {

        String skldSimiskra = null;
        String skldSimRaduga= null;
        int ostatok =0 ;

        for (int i = 0;i<authorization_ttList.size();i++){
            if(authorization_ttList.get(i).getSimT2().equals("sim")){
                skldSimiskra = authorization_ttList.get(i).getShopIskra();
                skldSimRaduga = authorization_ttList.get(i).getName();
            }
        }



        for (int k = 0;k<remanisSimList.size();k++){
            if (!skldSimRaduga.equals(remanisSimList.get(k).getShop())){
                if (nameRainbow !=null && nameRainbow.equals(remanisSimList.get(k).getNameSimAndModem()) && !skldSimRaduga.equals(remanisSimList.get(k).getShop())||
                        nameSpark.equals(remanisSimList.get(k).getNameSimAndModem()) && !skldSimiskra.equals(remanisSimList.get(k).getShop())){
                    ostatok = ostatok +  remanisSimList.get(k).getRemainsSimModem();

                }
            }




        }





        return String.valueOf(ostatok);
    }

    private String remanisSkladSIM(String nameSpark, String nameRainbow) {
        String remanisSkladSIM = null;
        for (int i = 0;i<authorization_ttList.size();i++){
            if(authorization_ttList.get(i).getSimT2().equals("sim") ){
                for (int j = 0;j<remanisSimList.size();j++){
                    if(nameSpark.equals(remanisSimList.get(j).getNameSimAndModem()) && authorization_ttList.get(i).getShopIskra().equals(remanisSimList.get(j).getShop())||
                            nameRainbow!=null&&
                           nameRainbow.equals(remanisSimList.get(j).getNameSimAndModem()) && authorization_ttList.get(i).getName().equals(remanisSimList.get(j).getShop())){
                        remanisSkladSIM = String.valueOf(remanisSimList.get(j).getRemainsSimModem());
                    }
                }
            }
        }
         return remanisSkladSIM;
    }

    private String planVypol(String sale_1, int plan) {
        return Integer.parseInt(sale_1) * 100 / plan + " " + "%";
    }

    public int plan(String sale_6) {
        int plan = Integer.parseInt(sale_6) * 110 / 100;
        if (plan < 2) {
            plan = 2;
        }
        return plan;
    }

    private String Sele(String shopIskra, String shopRaduga, String simIskra, String simRaduga) {
        String sale_1 = String.valueOf(0);
        for (int i = 0; i < saleSim_1ms.size(); i++) {
            if (shopIskra.equals(saleSim_1ms.get(i).getShop()) && simIskra.equals(saleSim_1ms.get(i).getNameSimAndModem()) || shopRaduga.equals(saleSim_1ms.get(i).getShop()) && simRaduga.equals(saleSim_1ms.get(i).getNameSimAndModem())) {
                sale_1 = String.valueOf(saleSim_1ms.get(i).getRemainsSimModem());
            }
        }
        return sale_1;
    }

    private String sale_6(String shopIskra, String shopRaduga, String simIskra, String simRaduga) {
        String sale_6 = String.valueOf(0);
        for (int i = 0; i < saleSim_6ms.size(); i++) {
            if (shopIskra.equals(saleSim_6ms.get(i).getShop()) && simIskra.equals(saleSim_6ms.get(i).getNameSimAndModem()) || shopRaduga.equals(saleSim_6ms.get(i).getShop()) && simRaduga.equals(saleSim_6ms.get(i).getNameSimAndModem())) {
                sale_6 = String.valueOf(saleSim_6ms.get(i).getRemainsSimModem() / 6);
            }
        }
        return sale_6;
    }

    private int idDlan() {


        return 5;
    }

    public List<SimSvod> ApiSimAndroid() {
        //List<SimSvod> simSvodList = new ArrayList<>();


        return simSvodList;
    }
}
