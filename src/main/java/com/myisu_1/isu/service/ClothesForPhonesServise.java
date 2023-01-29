package com.myisu_1.isu.service;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatching;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import com.myisu_1.isu.models.ClothesForPhones.Glass.Glass;
import com.myisu_1.isu.models.authorization_tt;
import com.myisu_1.isu.repo.ClothingMatchingTableRepositoriy;
import com.myisu_1.isu.repo.PhoneRepositoriy;
import com.myisu_1.isu.repo.PostRepositoriy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesForPhonesServise {
    @Autowired
    public PostRepositoriy authorization_tt;
    @Autowired
    private PhoneRepositoriy phoneRepositoriy;
    @Autowired
    private ClothingMatchingTableRepositoriy clothingMatchingTableRepositoriy;

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
        clothingMatching.clothingMatchingTableList = sim;
        clothingMatchingTableRepositoriy.saveAll(clothingMatching.clothingMatchingTableList);
        clothingMatching.clothingMatchingTableList = clothingMatchingTableRepositoriy.findAll();
      return clothingMatching.clothingMatchingTableList;
    }
}
