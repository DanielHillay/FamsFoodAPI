package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByCustomerId(String customerId);
}
