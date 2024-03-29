package com.myisu_1.isu.repo;


import com.myisu_1.isu.models.Barcode.DocUnf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocUnfRepository extends JpaRepository<DocUnf,Integer> {

    @Query("SELECT new com.myisu_1.isu.models.Barcode.DocUnf( p.barcode, x.nomenclature, c.quantity, c.price) FROM DocUnf c " +
            "JOIN c.barcodeUnfs p JOIN p.barcodeSparks x WHERE x.barcode IN ?1")
    List<DocUnf> shkDocUnf(List<String> barcode);

    @Query("SELECT p.barcode FROM DocUnf c " +
            "JOIN c.barcodeUnfs p JOIN p.barcodeSparks x WHERE c.barcode = '' OR c.barcode = 'null'")
    List<String> shkDocUnfs();
}
