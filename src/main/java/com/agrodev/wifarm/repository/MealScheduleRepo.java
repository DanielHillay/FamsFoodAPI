package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealScheduleRepo extends JpaRepository<MealSchedule, Long> {
}
