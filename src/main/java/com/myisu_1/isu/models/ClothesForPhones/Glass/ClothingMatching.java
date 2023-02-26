package com.myisu_1.isu.models.ClothesForPhones.Glass;


import com.myisu_1.isu.models.Shop.Shop;
import com.myisu_1.isu.models.authorization_tt;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClothingMatching extends Shop {

    public List<ClothingMatchingTable> clothingMatchingTableList;
    public List<Glass> brendRemanisList;
    List<Glass> warehouseRemnants;


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
System.out.println(сlothesForPhonesRemanis.size());
        return сlothesForPhonesRemanis;
    }

    public List<ClothesForPhonesSale6> creatClothingMatchingSale6(MultipartFile clothingMatching) throws IOException {

        List<ClothesForPhonesSale6> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesSale6>();
        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            ClothesForPhonesSale6  clothes = new ClothesForPhonesSale6();
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
                if (remPhone.getBrend().equals(remlothes.getBrend())){
                    brendRemanis.setRemanisCloters(remlothes.getRemanis());
                }
            }
            for (Glass sale6Clothes : sal6Clothes) {
                if (remPhone.getBrend().equals(sale6Clothes.getBrend())){
                    brendRemanis.setSale6(sale6Clothes.getRemanis());
                }
            }
            for (Glass sale1Clothes : sal1Clothes) {
                if(remPhone.getBrend().equals(sale1Clothes.getBrend())){
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
                if (remPhone.getName().equals(remlothes.getBrend())){
                    brendRemanis.setRemanisCloters(remlothes.getRemanis());
                }
            }
            for (Glass sale6Clothes : glasses) {
                if (remPhone.getName().equals(sale6Clothes.getBrend())){
                    brendRemanis.setRemanis(sale6Clothes.getRemanis());
                }
            }



            brendRemanisList.add(brendRemanis);


        }
      //  System.out.println(brendRemanisList);
    }

    public List<List<Glass>> loadPhoneRemanisShop(List<String> brendList, List<Glass> brendShop, List<Glass> phoneRemanisShop, List<String> clothingList) {
        List<Glass> clothing = new ArrayList<>();
        List<Glass> phone = new ArrayList<>();
        List<List<Glass>> listRemanis = new ArrayList<>();
        for (String clothings : clothingList) {
            Glass clothingShop = new Glass();
            clothingShop.setBrend(clothings);

            for (Glass brendShops : brendShop) {
                if (brendShops.getBrend().equals(clothings)){
                    clothingShop.setRemanis(brendShops.getRemanisCloterse());
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

                if (phoneShops.getBrend().equals(brends)){
                    brendsShop.setRemanis( phoneShops.getRemanis());
                }
            }
            phone.add(brendsShop);
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

                if (phoneShops.getBrend().equals(brends)){
                    brendsShop.setRemanis( phoneShops.getRemanis());
                }
            }
            for (Glass phoneShops : remanisClothes) {

                if (phoneShops.getBrend().equals(brends)){
                    brendsShop.setRemanisCloters( phoneShops.getRemanis());
                }
            }
            phone.add(brendsShop);
        }

        return phone;
    }

    public List<Glass> loadWarehouseRemnants(List<Glass> brendShop, List<String> clothingList) {
        long sum = 0;
        warehouseRemnants = new ArrayList<>();
        for (String brends : clothingList) {
            Glass brendsShop = new Glass();
            brendsShop.setBrend(brends);
            brendsShop.setRemanis(0);
            for (Glass phoneShops : brendShop) {

                if (phoneShops.getBrend().equals(brends)){
                    System.out.println(phoneShops.getBrend()+"--"+brends);
                    brendsShop.setRemanis( phoneShops.getRemanisCloterse());
                    sum +=  phoneShops.getRemanisCloterse();

                }
            }

            warehouseRemnants.add(brendsShop);
        }
        warehouseRemnants.add(new Glass("Итого", sum));
       return warehouseRemnants;
    }

    public List<Glass> movingWarehouse(String shop, String kol, String view) {

        return warehouseRemnants;
    }
}