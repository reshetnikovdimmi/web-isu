package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Phone_Smart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneRepositoriy extends JpaRepository<Phone_Smart, Integer> {
    @Query("SELECT DISTINCT Brend FROM Phone_Smart")
    List<String> getBrendDisting();

}
