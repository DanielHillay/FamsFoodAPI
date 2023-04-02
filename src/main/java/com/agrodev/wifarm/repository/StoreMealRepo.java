package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.StoreMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMealRepo extends JpaRepository<StoreMeal, Long> {
}
