package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Phone_Smart;
import com.myisu_1.isu.models.price_promo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PromoRepositoriy extends PagingAndSortingRepository<price_promo, Integer> {

   //("SELECT  FROM price_promo ORDER BY price_promo.start_promo DESC")


 //   List<price_promo> getPhone_Smart();
}
