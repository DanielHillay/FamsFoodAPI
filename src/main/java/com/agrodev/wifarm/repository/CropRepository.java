package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.Crops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Supplier;

@Repository
public interface CropRepository extends JpaRepository<Crops, Long> {
    Optional<Crops> findByCropName(String cropName);
}
