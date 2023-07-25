package com.myisu_1.isu.service;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatching;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class ClothingMatchingServise {
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

        clothesForPhonesSale6Repositoriy.deleteAll();

        clothesForPhonesSale6Repositoriy.saveAll(glass.creatClothingMatchingSale6(clothingMatchingSale6));
    }

    public List<Glass> remainderSaleClothing(String view) {


        glass.loadRemainderSaleClothing(phoneRepositoriy.getBrendRemanis(),clothingMatchingTableRepositoriy.getRemanisClothes(view),clothingMatchingTableRepositoriy.getSal6Clothes(view),clothingMatchingTableRepositoriy.getSal1Clothes(view));

      return glass.brendRemanisList;
    }

    public List<Glass> tableShopRemanis(String s, String view ) {

        glass.authorization_ttList = (List<authorization_tt>) authorization_tt.findAll();

        glass.loadTableShopRemanis(phoneRepositoriy.getBrendShopRemanis(s),clothingMatchingTableRepositoriy.tableShopRemanis(s,view));

        return glass.brendRemanisList;
    }

    public List<List<Glass>> tableShopBrend(String shop, String brend, String view) {


        return glass.loadPhoneRemanisShop(clothingMatchingTableRepositoriy.getBrendList(clothingMatchingTableRepositoriy.getClothingList(brend,view)),clothingMatchingTableRepositoriy.getBrendShop(brend,view,shop),phoneRepositoriy.getPhoneRemanisShop(shop),clothingMatchingTableRepositoriy.getClothingList(brend,view),shop);

    }

    public List<Glass> tableOrderЕable(String brend, String view) {


      return glass.loadOrderTable(clothingMatchingTableRepositoriy.getBrendList(clothingMatchingTableRepositoriy.getClothingList(brend,view)),phoneRepositoriy.getBrendRemanis(),clothingMatchingTableRepositoriy.getRemanisClothes(view));
    }

    public List<Glass> warehouseRemnants(String shop,String brend, String view) {


        return glass.loadWarehouseRemnants(clothingMatchingTableRepositoriy.getClothingAll(view,shop),clothingMatchingTableRepositoriy.getClothingList(brend,view));

    }

    public List<Glass> movingWarehouse(String models, String brend, String kol, String view, String shop) {

      return glass.movingWarehouse(brend,kol,clothingMatchingTableRepositoriy.getClothingList(models,view),shop);

    }

    public HashMap<String, List<List<Glass>>> exselFileExporteClotingPhone() {

        return glass.shopRemanis;
    }
}
