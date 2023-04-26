package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealScheduleRepo extends JpaRepository<MealSchedule, Long> {
    List<MealSchedule> findAllByUserId(String userId);

    Optional<MealSchedule> findByUserId(String userId);
}
