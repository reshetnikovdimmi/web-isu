package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothesForPhonesRemanis;
import com.myisu_1.isu.models.ClothesForPhones.Glass.ClothingMatchingTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesForPhonesRepositoriy extends JpaRepository<ClothesForPhonesRemanis, Integer> {
}
