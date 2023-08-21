package com.myisu_1.isu.repo;

import com.myisu_1.isu.models.Barcode.BarcodeSpark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface BarcodeSparkRepository extends JpaRepository<BarcodeSpark,Integer> {

}
