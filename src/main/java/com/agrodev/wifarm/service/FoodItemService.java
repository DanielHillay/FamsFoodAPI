package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.FoodItems;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public ResponseEntity<StandardResponse> createFoodItem(FoodItems foodItems) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", foodItemRepository.save(foodItems));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create food item");
        }
    }

    public ResponseEntity<StandardResponse> updateFoodItem(FoodItems foodItems) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", foodItemRepository.save(foodItems));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create food item");
        }
    }

    public ResponseEntity<StandardResponse> getFoodItem(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", foodItemRepository.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create food item");
        }
    }

    public ResponseEntity<StandardResponse> getAllFoodItems() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", foodItemRepository.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all food items");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllFoodItems() {
        try {
            foodItemRepository.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete all food items");
        }
    }

    public ResponseEntity<StandardResponse> deleteFoodItem(Long id) {
        try {
            foodItemRepository.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not delete all food items");
        }
    }
}
