package com.myisu_1.isu.models.ClothesForPhones.Glass;


import com.myisu_1.isu.models.Shop.Shop;
import com.myisu_1.isu.models.authorization_tt;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClothingMatching extends Shop {

    public List<ClothingMatchingTable> clothingMatchingTableList;
    public List<Glass> brendRemanisList;
    public List<Glass> warehouseRemnants;
    public HashMap<String, List<List<Glass>>> shopRemanis;

    public List<ClothingMatchingTable> saveCloting(List<ClothingMatchingTable> sim) {
        clothingMatchingTableList = new ArrayList<>();
        for (ClothingMatchingTable num : sim) {

            clothingMatchingTableList.add(new ClothingMatchingTable(num.getViewClothes(), num.getNameClothes(), num.getPhoneClothes()));

        }

        return clothingMatchingTableList;
    }

    public List<ClothesForPhonesRemanis> creatClothingMatching(MultipartFile clothingMatching) throws IOException {
        List<ClothesForPhonesRemanis> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesRemanis>();
        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            ClothesForPhonesRemanis clothes = new ClothesForPhonesRemanis();
            XSSFRow row = worksheet.getRow(i);
            clothes.setNameShop(row.getCell(0).getStringCellValue());
            clothes.setNamesClothes(row.getCell(1).getStringCellValue());
            clothes.setRemanisClothes((int) row.getCell(2).getNumericCellValue());
            сlothesForPhonesRemanis.add(clothes);
        }

        return сlothesForPhonesRemanis;
    }

    public List<ClothesForPhonesSale1> creatClothingMatchingSale1(MultipartFile clothingMatching) throws IOException {
        List<ClothesForPhonesSale1> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesSale1>();
        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            ClothesForPhonesSale1 clothes = new ClothesForPhonesSale1();
            XSSFRow row = worksheet.getRow(i);
            clothes.setNameShop(row.getCell(0).getStringCellValue());
            clothes.setNameClothes(row.getCell(1).getStringCellValue());
            clothes.setSaleClothes((int) row.getCell(2).getNumericCellValue());
            сlothesForPhonesRemanis.add(clothes);
        }

        return сlothesForPhonesRemanis;
    }

    public List<ClothesForPhonesSale6> creatClothingMatchingSale6(MultipartFile clothingMatching) throws IOException {

        List<ClothesForPhonesSale6> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesSale6>();
        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            ClothesForPhonesSale6 clothes = new ClothesForPhonesSale6();
            XSSFRow row = worksheet.getRow(i);
            clothes.setNameShop(row.getCell(0).getStringCellValue());
            clothes.setNameClothes(row.getCell(1).getStringCellValue());
            clothes.setSaleClothes((int) row.getCell(2).getNumericCellValue());
            сlothesForPhonesRemanis.add(clothes);
        }

        return сlothesForPhonesRemanis;
    }

    public void loadRemainderSaleClothing(List<Glass> remanisPhone, List<Glass> remanisClothes, List<Glass> sal6Clothes, List<Glass> sal1Clothes) {
        brendRemanisList = new ArrayList<>();
        for (Glass remPhone : remanisPhone) {
            Glass brendRemanis = new Glass();

            brendRemanis.setBrend(remPhone.getBrend());
            brendRemanis.setRemanis(remPhone.getRemanis());
            for (Glass remlothes : remanisClothes) {
                if (remPhone.getBrend().equals(remlothes.getBrend())) {
                    brendRemanis.setRemanisCloters(remlothes.getRemanis());
                }
            }
            for (Glass sale6Clothes : sal6Clothes) {
                if (remPhone.getBrend().equals(sale6Clothes.getBrend())) {
                    brendRemanis.setSale6(sale6Clothes.getRemanis());
                }
            }
            for (Glass sale1Clothes : sal1Clothes) {
                if (remPhone.getBrend().equals(sale1Clothes.getBrend())) {
                    brendRemanis.setSale1(sale1Clothes.getRemanis());
                }
            }


            brendRemanisList.add(brendRemanis);


        }

    }

    public void loadTableShopRemanis(List<Glass> brendShopRemanis, List<Glass> glasses) {
        brendRemanisList = new ArrayList<>();

        for (authorization_tt remPhone : getAuthorization_ttList()) {
            Glass brendRemanis = new Glass();
            brendRemanis.setBrend(remPhone.getName());

            for (Glass remlothes : brendShopRemanis) {
                if (remPhone.getName().equals(remlothes.getBrend())) {
                    brendRemanis.setRemanisCloters(remlothes.getRemanis());
                }
            }
            for (Glass sale6Clothes : glasses) {
                if (remPhone.getName().equals(sale6Clothes.getBrend())) {
                    brendRemanis.setRemanis(sale6Clothes.getRemanis());
                }
            }


            brendRemanisList.add(brendRemanis);


        }
        //  System.out.println(brendRemanisList);
    }

    public List<List<Glass>> loadPhoneRemanisShop(List<String> brendList, List<Glass> brendShop, List<Glass> phoneRemanisShop, List<String> clothingList, String shop) {


        if (shopRemanis != null && shopRemanis.containsKey(shop) && checkingForT2(clothingList.get(0), shopRemanis.get(shop).get(0))) {

            return shopRemanis(shop,clothingList,brendList);
        } else if (shopRemanis != null && shopRemanis.containsKey(shop)) {
            for (String clothings : clothingList) {
                Glass clothingShop = new Glass();
                clothingShop.setBrend(clothings);

                for (Glass brendShops : brendShop) {
                    if (brendShops.getBrend().equals(clothings)) {
                        clothingShop.setRemanis(brendShops.getRemanisCloterse());
                        clothingShop.setRemanisCloters(0);
                    }
                }

                shopRemanis.get(shop).get(0).add(clothingShop);

            }
            for (String brends : brendList) {
                Glass brendsShop = new Glass();
                brendsShop.setBrend(brends);
                brendsShop.setRemanis(0);
                for (Glass phoneShops : phoneRemanisShop) {

                    if (phoneShops.getBrend().equals(brends)) {
                        brendsShop.setRemanis(phoneShops.getRemanis());
                    }
                }
                shopRemanis.get(shop).get(1).add(brendsShop);
            }
            shopRemanis.put(shop,shopRemanis.get(shop));

            return shopRemanis(shop,clothingList, brendList);
        } else {

            List<Glass> clothing = new ArrayList<>();
            List<Glass> phone = new ArrayList<>();
            List<List<Glass>> listRemanis = new ArrayList<>();
            if (shopRemanis == null) {
                shopRemanis = new HashMap<>();
            }


            for (String clothings : clothingList) {
                Glass clothingShop = new Glass();
                clothingShop.setBrend(clothings);

                for (Glass brendShops : brendShop) {
                    if (brendShops.getBrend().equals(clothings)) {
                        clothingShop.setRemanis(brendShops.getRemanisCloterse());
                        clothingShop.setRemanisCloters(0);
                    }
                }
                clothing.add(clothingShop);
            }
            listRemanis.add(clothing);

            for (String brends : brendList) {
                Glass brendsShop = new Glass();
                brendsShop.setBrend(brends);
                brendsShop.setRemanis(0);
                for (Glass phoneShops : phoneRemanisShop) {

                    if (phoneShops.getBrend().equals(brends)) {
                        brendsShop.setRemanis(phoneShops.getRemanis());
                    }
                }
                phone.add(brendsShop);
            }
            listRemanis.add(phone);
            shopRemanis.put(shop, listRemanis);


        }
        return shopRemanis(shop, clothingList, brendList);
    }

    private List<List<Glass>> shopRemanis(String shop, List<String> clothingList, List<String> brendShop) {
        List<Glass> clothing = new ArrayList<>();
        List<Glass> phone = new ArrayList<>();
        List<List<Glass>> listRemanis = new ArrayList<>();
        for (Glass brendShops : shopRemanis.get(shop).get(0)) {
            for (String brends : clothingList) {
                if (brendShops.Brend.equals(brends)) {
                    clothing.add(new Glass(brendShops.Brend,brendShops.getRemanis(),brendShops.getRemanisCloters()));
                }

            }
        }
        listRemanis.add(clothing);
        for (Glass brendShops : shopRemanis.get(shop).get(1)) {
            for (String brends : brendShop) {
                if (brendShops.Brend.equals(brends)) {
                    phone.add(new Glass(brendShops.Brend,brendShops.getRemanis(),brendShops.getRemanisCloters()));
                }

            }
        }
        listRemanis.add(phone);

        return listRemanis;
    }

    public List<Glass> loadOrderTable(List<String> brendList, List<Glass> brendRemanis, List<Glass> remanisClothes) {

        List<Glass> phone = new ArrayList<>();


        for (String brends : brendList) {
            Glass brendsShop = new Glass();
            brendsShop.setBrend(brends);
            brendsShop.setRemanis(0);
            for (Glass phoneShops : brendRemanis) {

                if (phoneShops.getBrend().equals(brends)) {
                    brendsShop.setRemanis(phoneShops.getRemanis());
                }
            }
            for (Glass phoneShops : remanisClothes) {

                if (phoneShops.getBrend().equals(brends)) {
                    brendsShop.setRemanisCloters(phoneShops.getRemanis());
                }
            }
            phone.add(brendsShop);
        }

        return phone;
    }

    public List<Glass> loadWarehouseRemnants(List<Glass> brendShop, List<String> clothingList) {
        if (warehouseRemnants == null) {
            warehouseRemnants = new ArrayList<>();
        }
        if (warehouseRemnants != null && checkingForT2(clothingList.get(0),warehouseRemnants)) {

            return distributionList(clothingList);
        } else {
            long sum = 0;

            for (String brends : clothingList) {
                Glass brendsShop = new Glass();
                brendsShop.setBrend(brends);
                brendsShop.setRemanis(0);
                for (Glass phoneShops : brendShop) {

                    if (phoneShops.getBrend().equals(brends)) {

                        brendsShop.setRemanis(phoneShops.getRemanisCloterse());
                        sum += phoneShops.getRemanisCloterse();

                    }
                }

                warehouseRemnants.add(brendsShop);
            }
            //    warehouseRemnants.add(new Glass("Итого", sum));
            return distributionList(clothingList);
        }

    }

    public List<Glass> movingWarehouse(String models, String kol, List<String> clothingList, String shop) {

        long ost = 0;
        for (int i = 0; i < warehouseRemnants.size(); i++) {

            if (warehouseRemnants.get(i).getBrend().equals(models)) {
                ost = (warehouseRemnants.get(i).getRemanis() - Integer.parseInt(kol));

                warehouseRemnants.set(i, new Glass(warehouseRemnants.get(i).getBrend(), ost));
            }

        }


        for (int i = 0;i<shopRemanis.get(shop).get(0).size();i++) {
            if (models.equals(shopRemanis.get(shop).get(0).get(i).getBrend())) {

                shopRemanis.get(shop).get(0).set(i,new Glass(shopRemanis.get(shop).get(0).get(i).getBrend(),shopRemanis.get(shop).get(0).get(i).getRemanis(),Integer.parseInt(kol)));
            }

        }
        shopRemanis.put(shop,shopRemanis.get(shop));

        return distributionList(clothingList);
    }

    public List<Glass> distributionList(List<String> clothingList) {

        long sum = 0;
        List<Glass> distributionList = new ArrayList<>();
        for (String brends : clothingList) {
            for (Glass brend : warehouseRemnants) {
                if (brends.equals(brend.getBrend())) {
                    distributionList.add(brend);
                    sum += brend.getRemanis();
                }
            }
        }
        distributionList.add(new Glass("Итого", sum));
        return distributionList;
    }

    public boolean checkingForT2(String name, List<Glass> warehouseRemnants) {
        boolean t2 = false;
        Glass james = warehouseRemnants
                .stream()
                .filter(customer -> name.equals(customer.getBrend()))
                .findAny()
                .orElse(null);
        if (james != null) {
            t2 = true;
        } else {
            t2 = false;
        }
        return t2;
    }
}