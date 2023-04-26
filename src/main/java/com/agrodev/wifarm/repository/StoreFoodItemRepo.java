package com.agrodev.wifarm.repository;


import com.agrodev.wifarm.entity.StoreFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreFoodItemRepo extends JpaRepository<StoreFoodItem, Long> {
    Optional<StoreFoodItem> findByItemName(String itemName);
}
