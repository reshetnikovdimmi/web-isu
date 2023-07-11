package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Marwel.MarvelClassifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarvelClassifierRepositoriy extends JpaRepository<MarvelClassifier, Integer> {

    @Query("SELECT RainbowNomenclature FROM MarvelClassifier")
    List<String> getRainbowNomenclature();
}
