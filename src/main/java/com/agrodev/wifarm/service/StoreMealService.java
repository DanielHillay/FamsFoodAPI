package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.StoreMealRequest;
import com.agrodev.wifarm.repository.StoreFoodItemRepo;
import com.agrodev.wifarm.repository.StoreMealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
public class StoreMealService {
    @Autowired
    private StoreMealRepo storeMealRepo;
    @Autowired
    private StoreFoodItemRepo storeFoodItemRepo;

    public ResponseEntity<StandardResponse> createStoreMeal(StoreMealRequest request) {
        try {
            Random customRand = new Random();
            Set<StoreFoodItem> foodItems = new HashSet<>();
            double mealWeight = 0;
            double mealPrice = 0;
            StoreMeal meal = new StoreMeal();
            for(Map.Entry<String, Integer> entry : request.getFoodItems().entrySet()){
                StoreFoodItem storeFoodItem = storeFoodItemRepo.findByItemName(entry.getKey()).get();
                foodItems.add(storeFoodItem);
                mealPrice = mealPrice + storeFoodItem.getItemPrice();
                mealWeight = mealWeight + (entry.getValue() * storeFoodItem.getWeight());
            }
            meal.setMealName(request.getMealName().isEmpty() ? request.getUserId() : request.getMealName());
            meal.setUserId(request.getUserId());
            meal.setWeight(mealWeight);
            meal.setMealPrice(mealPrice);
            meal.setMealId(String.format("%08d", customRand.nextInt(10000)));
            meal.setFoodItems(foodItems);

            storeMealRepo.save(meal);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create user meal");
        }
    }

    public ResponseEntity<StandardResponse> updateStoreMeal(StoreMeal storeMeal) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.save(storeMeal));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> getStoreMeal(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> getAllStoreMeals() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> deleteStoreMeal(Long id) {
        try {
            storeMealRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllStoreMeals() {
        try {
            storeMealRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> topUpMeal(String mealId, double weight) {
        try {
            StoreMeal meal = storeMealRepo.updateMealWeight(mealId, weight);
            return StandardResponse.sendHttpResponse(true, "Successful", meal);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not top up meal");
        }
    }
}
