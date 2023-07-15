package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.retail_price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PriceRepositoriy extends JpaRepository<retail_price, Integer> {


}
