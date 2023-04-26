package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.StoreMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Repository
public interface StoreMealRepo extends JpaRepository<StoreMeal, Long> {
    Optional<StoreMeal> findByMealId(String mealId);

    @Transactional
    @Modifying
    @Query(value = "update StoreMeal s set s.quantityAvailable = :weight where s.mealId = :mealId")
    StoreMeal updateMealWeight(@Param("mealId") String mealId, @Param("weight") double weight);
}
