package com.myisu_1.isu.service;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatching;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.ClothesForPhonesRepositoriy;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.apache.poi.ss.usermodel.CellType;
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
import java.util.List;
import java.util.TimeZone;

@Service
public class ClothesForPhonesServise {
    @Autowired
    public PostRepositoriy authorization_tt;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;
    @Autowired
    private ClothesForPhonesRepositoriy clothesForPhonesRepositoriy;

    Glass glass;
    ClothingMatching clothingMatching;
  public Iterable<authorization_tt> Loading(){
        glass = new Glass();
        glass.authorization_ttList = (List<authorization_tt>) authorization_tt.findAll();
        glass.phoneSmartList = phoneRepositoriy.findAll();
        glass.brendDisting = phoneRepositoriy.getBrendDisting();
        glass.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
        return glass.print();
    }
     public List<String> LoadingBrendDisting(){
         clothingMatching = new ClothingMatching();

         clothingMatching.brendDisting = phoneRepositoriy.getBrendDisting();

         clothingMatching.prints();
         return clothingMatching.brendDisting;
     }

    public List<ClothingMatchingTable> saveSparkSale(List<ClothingMatchingTable> sim) {
        clothingMatching = new ClothingMatching();
        clothingMatching.clothingMatchingTableList = sim;
        clothingMatchingTableRepositoriy.saveAll(clothingMatching.clothingMatchingTableList);
        clothingMatching.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
      return clothingMatching.clothingMatchingTableList;
    }
    public List<ClothingMatchingTable> slotongMatchingTable() {
        clothingMatching.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
        return clothingMatching.clothingMatchingTableList;
    }

    public void LoadingClothingMatching(MultipartFile clothingMatching) throws IOException {

        List<ClothesForPhonesRemanis> сlothesForPhonesRemanis = new ArrayList<ClothesForPhonesRemanis>();
        XSSFWorkbook workbook = new XSSFWorkbook(clothingMatching.getInputStream());

        XSSFSheet worksheet = workbook.getSheetAt(0);

        clothesForPhonesRepositoriy.deleteAll();
        for (int i = 2; i < worksheet.getPhysicalNumberOfRows() - 1; i++) {

            ClothesForPhonesRemanis clothes = new ClothesForPhonesRemanis();
            XSSFRow row = worksheet.getRow(i);
            clothes.setNameShop(row.getCell(0).getStringCellValue());
            clothes.setNameClothes(row.getCell(1).getStringCellValue());
            clothes.setRemanisClothes(String.valueOf(row.getCell(2).getNumericCellValue()));

            сlothesForPhonesRemanis.add(clothes);
        }
        System.out.println(сlothesForPhonesRemanis.size());
        clothesForPhonesRepositoriy.saveAll(сlothesForPhonesRemanis);


    }
}
