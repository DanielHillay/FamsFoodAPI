package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.UserMealRequest;
import com.agrodev.wifarm.repository.FoodItemRepository;
import com.agrodev.wifarm.repository.StoreFoodItemRepo;
import com.agrodev.wifarm.repository.UserMealRepository;
import com.agrodev.wifarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
public class UserMealService {
    @Autowired
    private UserMealRepository userMealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoreFoodItemRepo storeFoodItemRepo;
    @Autowired
    private FoodItemRepository foodItemRepository;

    public ResponseEntity<StandardResponse> createUserMeal(UserMealRequest request) {
        try {
            Random customRand = new Random();
            double mealWeight = 0;
            double mealPrice = 0;
            UserMeal meal = new UserMeal();
            User user = userRepository.findByUserId(request.getUserId()).get();
            for(Map.Entry<String, Integer> entry : request.getFoodItems().entrySet()){
                StoreFoodItem storeFoodItem = storeFoodItemRepo.findByItemName(entry.getKey()).get();
                FoodItems foodItem = new FoodItems();
                foodItem.setWeight(storeFoodItem.getWeight());
                foodItem.setItemName(storeFoodItem.getItemName());
                foodItem.setItemCategory(storeFoodItem.getItemCategory());
                foodItem.setImageUrl(storeFoodItem.getImageUrl());
                foodItem.setItemId(String.format("%08d", customRand.nextInt(1000000)));
                foodItem.setItemPrice(storeFoodItem.getItemPrice());
                foodItem.setUnits(entry.getValue());
                foodItem.setQuantityInKG(entry.getValue() * storeFoodItem.getWeight());
                meal.getFoodItems().add(foodItemRepository.save(foodItem));
                mealPrice = mealPrice + storeFoodItem.getItemPrice();
                mealWeight = mealWeight + (entry.getValue() * storeFoodItem.getWeight());
            }
            meal.setMealName(request.getMealName().isEmpty() ? request.getUserId() : request.getMealName());
            meal.setUserId(request.getUserId());
            meal.setLGA(user.getLGA());
            meal.setCountry(user.getCountry());
            meal.setState(user.getState());
            meal.setTown(user.getTown());
            meal.setWeight(mealWeight);
            meal.setMealPrice(mealPrice);

            userMealRepository.save(meal);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create user meal");
        }
    }
}
