package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.entity.UserMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMealRepository extends JpaRepository<UserMeal, Long> {
    Optional<UserMeal> findByMealId(String mealId);
}
