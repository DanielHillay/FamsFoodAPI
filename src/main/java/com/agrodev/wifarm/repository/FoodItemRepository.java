package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItems, Long> {
}
