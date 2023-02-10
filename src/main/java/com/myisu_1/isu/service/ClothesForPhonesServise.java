package com.myisu_1.isu.service;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatching;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.SIM.SaleSim_6m;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
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
    @Autowired
    private ClothesForPhonesSale1Repositoriy clothesForPhonesSale1Repositoriy;
    @Autowired
    private ClothesForPhonesSale6Repositoriy clothesForPhonesSale6Repositoriy;
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


         return clothingMatching.brendDisting;
     }

    public List<ClothingMatchingTable> saveSparkSale(List<ClothingMatchingTable> sim) {
        clothingMatching = new ClothingMatching();
        clothingMatching.saveCloting(sim);
        clothingMatchingTableRepositoriy.saveAll(clothingMatching.clothingMatchingTableList);
        clothingMatching.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
      return clothingMatching.clothingMatchingTableList;
    }
    public List<ClothingMatchingTable> slotongMatchingTable() {
        clothingMatching.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
        return clothingMatching.clothingMatchingTableList;
    }

    public void LoadingClothingMatching(MultipartFile clothingMatchingRemanis) throws IOException {
        clothingMatching = new ClothingMatching();
       clothesForPhonesRepositoriy.deleteAll();
       clothesForPhonesRepositoriy.saveAll(clothingMatching.creatClothingMatching(clothingMatchingRemanis));
    }
    public void LoadingClothingMatchingSale1(MultipartFile clothingMatchingSale1) throws IOException {
        clothingMatching = new ClothingMatching();
        clothesForPhonesSale1Repositoriy.deleteAll();
        clothesForPhonesSale1Repositoriy.saveAll(glass.creatClothingMatchingSale1(clothingMatchingSale1));
    }

    public void LoadingClothingMatchingSale6(MultipartFile clothingMatchingSale6) throws IOException {
        glass = new Glass();
        clothesForPhonesSale6Repositoriy.deleteAll();
        clothesForPhonesSale6Repositoriy.saveAll(glass.creatClothingMatchingSale6(clothingMatchingSale6));
    }
}
