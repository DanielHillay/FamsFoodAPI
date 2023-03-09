package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MarketCrops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface MarketCropsRepo extends JpaRepository<MarketCrops, Long> {
    Optional<MarketCrops> findByCropName(String cropName);
}
