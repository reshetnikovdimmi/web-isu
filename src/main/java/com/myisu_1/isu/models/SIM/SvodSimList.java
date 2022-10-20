package com.myisu_1.isu.models.SIM;

import com.myisu_1.isu.models.RTK.DistribModelRTK;
import com.myisu_1.isu.models.RTK.MatrixRTK;

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
            simSvodList1.add(new SimSvod(0, 0, shop, "0", "0", String.valueOf(0), "0", 0, "0", null, "0", "0", "0"));
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
            String remanisSkladSIM = remanisSkladSIM(simAndRtkTables.get(i).getNameSpark(), simAndRtkTables.get(i).getNameRainbow());
            String remanisSIM = remanisSIM(simAndRtkTables.get(i).getNameSpark(), simAndRtkTables.get(i).getNameRainbow());
            String averageSalesSIM = averageSalesSIM(simAndRtkTables.get(i).getNameSpark(), simAndRtkTables.get(i).getNameRainbow());
            String recommendedToOrder = recommendedToOrder(remanisSkladSIM, averageSalesSIM);
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
        int recommendedToOrder = 0;
        int rekzakaz = 0;
        if (remanisSkladSIM != null && Integer.parseInt(averageSalesSIM) > Integer.parseInt(remanisSkladSIM)) {
            recommendedToOrder = Integer.parseInt(remanisSkladSIM);

            // System.out.println(averageSalesSIM+"--"+remanisSkladSIM);
            if (remanisSkladSIM != null) {
                while (Integer.parseInt(averageSalesSIM) > recommendedToOrder) {

                    recommendedToOrder = recommendedToOrder + 50;
                    rekzakaz = rekzakaz + 50;


                }

            }
        }

        return String.valueOf(rekzakaz);
    }

    private String averageSalesSIM(String nameSpark, String nameRainbow) {
        int averageSalesSIM = 0;
        for (int i = 0; i < simSvodList.size(); i++) {
            if (nameSpark.equals(simSvodList.get(i).getNameSim()) || nameRainbow != null && nameRainbow.equals(simSvodList.get(i).getNameSim())) {
                averageSalesSIM = averageSalesSIM + Integer.parseInt(simSvodList.get(i).getSale_6());
            }

        }


        return String.valueOf(averageSalesSIM);
    }

    private String remanisSIM(String nameSpark, String nameRainbow) {

        String skldSimiskra = null;
        String skldSimRaduga = null;
        int ostatok = 0;

        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (authorization_ttList.get(i).getSimT2().equals("sim")) {
                skldSimiskra = authorization_ttList.get(i).getShopIskra();
                skldSimRaduga = authorization_ttList.get(i).getName();
            }
        }


        for (int k = 0; k < remanisSimList.size(); k++) {
            if (!skldSimRaduga.equals(remanisSimList.get(k).getShop())) {
                if (nameRainbow != null && nameRainbow.equals(remanisSimList.get(k).getNameSimAndModem()) && !skldSimRaduga.equals(remanisSimList.get(k).getShop()) ||
                        nameSpark.equals(remanisSimList.get(k).getNameSimAndModem()) && !skldSimiskra.equals(remanisSimList.get(k).getShop())) {
                    ostatok = ostatok + remanisSimList.get(k).getRemainsSimModem();

                }
            }


        }


        return String.valueOf(ostatok);
    }

    private String remanisSkladSIM(String nameSpark, String nameRainbow) {
        String remanisSkladSIM = null;
        for (int i = 0; i < authorization_ttList.size(); i++) {
            if (authorization_ttList.get(i).getSimT2().equals("sim")) {
                for (int j = 0; j < remanisSimList.size(); j++) {
                    if (nameSpark.equals(remanisSimList.get(j).getNameSimAndModem()) && authorization_ttList.get(i).getShopIskra().equals(remanisSimList.get(j).getShop()) ||
                            nameRainbow != null &&
                                    nameRainbow.equals(remanisSimList.get(j).getNameSimAndModem()) && authorization_ttList.get(i).getName().equals(remanisSimList.get(j).getShop())) {
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

    public List<MatrixRTK> MatrixRTK() {

        List<MatrixRTK> matrixRTKArrayList = new ArrayList<>();

        for (int i = 0; i < authorization_ttList.size(); i++) {
            for (int j = 0; j < matrixRTKList.size(); j++) {
                if (authorization_ttList.get(i).getClusterRtk().equals(matrixRTKList.get(j).getCluster())) {
                    String nameShop = authorization_ttList.get(i).getName();
                    Cluster(matrixRTKList.get(j).getCluster());

                    String internalLov = distribModelRTK(nameShop, DistribModelRTK.InternalLov.getModelRTK(), DistribModelRTK.InternalLov.getCluster());
                    String internalMiddle = distribModelRTK(nameShop, DistribModelRTK.InternalMiddle.getModelRTK(), DistribModelRTK.InternalMiddle.getCluster());
                    String internalHigh = distribModelRTK(nameShop, DistribModelRTK.InternalHigh.getModelRTK(), DistribModelRTK.InternalHigh.getCluster());
                    String externalDome = distribModelRTK(nameShop, DistribModelRTK.ExternalDome.getModelRTK(), DistribModelRTK.ExternalDome.getCluster());
                    String externalCylindrical = distribModelRTK(nameShop, DistribModelRTK.ExternalCylindrical.getModelRTK(), DistribModelRTK.ExternalCylindrical.getCluster());
                    String externalHigh = distribModelRTK(nameShop, DistribModelRTK.ExternalHigh.getModelRTK(), DistribModelRTK.ExternalHigh.getCluster());
                    String externalWiFi = distribModelRTK(nameShop, DistribModelRTK.ExternalWiFi.getModelRTK(), DistribModelRTK.ExternalWiFi.getCluster());
                    String IPTVbox = distribModelRTK(nameShop, DistribModelRTK.IPTVbox.getModelRTK(), DistribModelRTK.IPTVbox.getCluster());
                    String miniColumn = distribModelRTK(nameShop, DistribModelRTK.MiniColumn.getModelRTK(), DistribModelRTK.MiniColumn.getCluster());
                    String capsuleColumn = distribModelRTK(nameShop, DistribModelRTK.CapsuleColumn.getModelRTK(), DistribModelRTK.CapsuleColumn.getCluster());
                    String rostelecomRouter = distribModelRTK(nameShop, DistribModelRTK.RostelecomRouter.getModelRTK(), DistribModelRTK.RostelecomRouter.getCluster());
                    String powerInjector = distribModelRTK(nameShop, DistribModelRTK.PowerInjector.getModelRTK(), DistribModelRTK.PowerInjector.getCluster());
                    String gameController = distribModelRTK(nameShop, DistribModelRTK.GameController.getModelRTK(), DistribModelRTK.GameController.getCluster());
                    String[] sufficiencyList = {internalLov, internalMiddle, internalHigh, externalDome, externalCylindrical, externalHigh, externalWiFi, IPTVbox, miniColumn, rostelecomRouter, powerInjector, gameController};
                    String sufficiency = Sufficiency(sufficiencyList);

                    matrixRTKArrayList.add(new MatrixRTK(authorization_ttList.get(i).getName(),
                            internalLov,
                            internalMiddle,
                            internalHigh,
                            externalDome,
                            externalCylindrical,
                            externalHigh,
                            externalWiFi,
                            IPTVbox,
                            miniColumn,
                            capsuleColumn,
                            rostelecomRouter,
                            powerInjector,
                            gameController,
                            sufficiency));

                }


            }

        }

        return matrixRTKArrayList;
    }

    private String Sufficiency(String[] sufficiencyList) {
        int count = 0;
        double sum = 0;

        for (int i = 0; i < sufficiencyList.length; i++) {
            if (sufficiencyList[i] != null && !sufficiencyList[i].isEmpty()) {
                count++;
                sum = sum + Double.parseDouble(sufficiencyList[i].replaceAll("%", ""));

            }

        }

        return String.format("%.1f",sum/count)+"%";
    }

    private void Cluster(String cluster) {
        for (int i = 0; i < matrixRTKList.size(); i++) {
            if (cluster.equals(matrixRTKList.get(i).getCluster())) {
                DistribModelRTK.InternalLov.setCluster(matrixRTKList.get(i).getVnutrLowVideoCam());
                DistribModelRTK.InternalMiddle.setCluster(matrixRTKList.get(i).getVideoCamVnutrMiddle());
                DistribModelRTK.InternalHigh.setCluster(matrixRTKList.get(i).getVnutrHighVideoCam());
                DistribModelRTK.ExternalDome.setCluster(matrixRTKList.get(i).getVneshKupolVideoCam());
                DistribModelRTK.ExternalCylindrical.setCluster(matrixRTKList.get(i).getVneshCylindrVideoCam());
                DistribModelRTK.ExternalHigh.setCluster(matrixRTKList.get(i).getVneshHighVideoCam());
                DistribModelRTK.ExternalWiFi.setCluster(matrixRTKList.get(i).getVneshWiFiVideoCam());
                DistribModelRTK.IPTVbox.setCluster(matrixRTKList.get(i).getIptvBox());
                DistribModelRTK.MiniColumn.setCluster(matrixRTKList.get(i).getSmartMiniSpeaker());
                DistribModelRTK.CapsuleColumn.setCluster(matrixRTKList.get(i).getSmartColumnCapsule());
                DistribModelRTK.RostelecomRouter.setCluster(matrixRTKList.get(i).getRostelecomRouter());
                DistribModelRTK.PowerInjector.setCluster(matrixRTKList.get(i).getPowerInjectorForVideoCam());
                DistribModelRTK.GameController.setCluster(matrixRTKList.get(i).getGameController());
            }

        }


    }


    private String distribModelRTK(String nameShop, String distribModelRTK, String cluster) {
        double remanis = 0;
        String remanisP = "";
        for (int i = 0; i < simAndRtkTables.size(); i++) {
            if (simAndRtkTables.get(i).getView().equals("RTK") && distribModelRTK.equals(simAndRtkTables.get(i).getDistributionModel())) {
                for (int j = 0; j < remanisSimList.size(); j++) {
                    if (nameShop.equals(remanisSimList.get(j).getShop()) && simAndRtkTables.get(i).getNameRainbow().equals(remanisSimList.get(j).getNameSimAndModem())) {
                        remanis = remanis + remanisSimList.get(j).getRemainsSimModem();

                    }
                }
            }

        }

        if (cluster != null && !cluster.isEmpty()) {
            if (Integer.parseInt(cluster) <= remanis) {
                remanisP = "100";
            } else {
                remanisP = String.valueOf(remanis / Double.parseDouble(cluster) * 100);

            }
        }

        if (!remanisP.isEmpty()) {
            remanisP = remanisP + "%";
        }

        return remanisP;
    }
}
