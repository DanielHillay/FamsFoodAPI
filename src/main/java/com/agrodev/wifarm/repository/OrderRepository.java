package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<MealOrder, Long> {
}
