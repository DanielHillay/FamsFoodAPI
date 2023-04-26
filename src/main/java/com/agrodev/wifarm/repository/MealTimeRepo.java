package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealTiming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealTimeRepo extends JpaRepository<MealTiming, Long> {
}
