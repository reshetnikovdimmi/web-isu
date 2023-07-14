package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Marwel.MarvelClassifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MarvelClassifierRepositoriy extends JpaRepository<MarvelClassifier, Integer> {

    @Query("SELECT RainbowNomenclature FROM MarvelClassifier")
    List<String> getRainbowNomenclature();
    @Modifying
    @Transactional
    @Query("update MarvelClassifier u set u.RainbowNomenclature = ?1 where u.RainbowNomenclature = ?2 ")
    void updatRainbowNomenclature(String model, String models);
}
