package com.myisu_1.isu.models.ClothesForPhones.Glass;

import com.myisu_1.isu.dto.BrendRemanis;
import com.myisu_1.isu.models.Shop.Shop;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClothingMatching extends Shop {

    public List<ClothingMatchingTable> clothingMatchingTableList;
    public List<BrendRemanis> brendRemanisList;


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

    public List<ClothesForPhonesSale6> creatClothingMatchingSale6(MultipartFile clothingMatching, List<ClothingMatchingTable> all) throws IOException {
        ClothesForPhonesSale6 clothes;
        List<ClothesForPhonesSale6> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesSale6>();

        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {
            clothes = new ClothesForPhonesSale6();
            XSSFRow row = worksheet.getRow(i);
            clothes.setNameShop(row.getCell(0).getStringCellValue());
            clothes.setNameClothes(row.getCell(1).getStringCellValue());
            clothes.setSaleClothes((int) row.getCell(2).getNumericCellValue());
            сlothesForPhonesRemanis.add(clothes);
        }

        return сlothesForPhonesRemanis;
    }

    public void loadRemainderSaleClothing(List<BrendRemanis> remanisPhone, List<BrendRemanis> remanisClothes, List<BrendRemanis> sal6Clothes, List<BrendRemanis> sal1Clothes) {
        brendRemanisList = new ArrayList<>();
        for (BrendRemanis remPhone : remanisPhone) {
            BrendRemanis brendRemanis = new BrendRemanis();
            brendRemanis.setBrend(remPhone.getBrend());
            brendRemanis.setRemanis(remPhone.getRemanis());
            for (BrendRemanis remlothes : remanisClothes) {
                if (remPhone.getBrend().equals(remlothes.getBrend())){
                    brendRemanis.setRemanisCloters(remlothes.getRemanis());
                }
            }
            for (BrendRemanis sale6Clothes : sal6Clothes) {
                if (remPhone.getBrend().equals(sale6Clothes.getBrend())){
                    brendRemanis.setSale6(sale6Clothes.getRemanis());
                }
            }
            for (BrendRemanis sale1Clothes : sal1Clothes) {
                if(remPhone.getBrend().equals(sale1Clothes.getBrend())){
                    brendRemanis.setSale1(sale1Clothes.getRemanis());
                }
            }


            brendRemanisList.add(brendRemanis);


        }

    }
}