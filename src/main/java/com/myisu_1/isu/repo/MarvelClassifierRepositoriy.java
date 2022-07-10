package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.MarvelClassifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarvelClassifierRepositoriy extends JpaRepository<MarvelClassifier, Integer> {
}
