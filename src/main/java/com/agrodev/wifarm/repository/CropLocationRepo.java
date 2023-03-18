package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.CropLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropLocationRepo extends JpaRepository<CropLocation, Long> {
    List<CropLocation> findByCropName(String cropName);
}
