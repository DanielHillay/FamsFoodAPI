package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTimeRepo extends JpaRepository<MealTime, Long> {
}
