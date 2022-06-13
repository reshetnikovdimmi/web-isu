package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.retail_price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepositoriy extends CrudRepository<retail_price, Integer> {
}
