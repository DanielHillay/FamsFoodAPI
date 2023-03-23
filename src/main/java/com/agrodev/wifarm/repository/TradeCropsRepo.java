package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.TradeCrops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeCropsRepo extends JpaRepository<TradeCrops, Long> {
}
