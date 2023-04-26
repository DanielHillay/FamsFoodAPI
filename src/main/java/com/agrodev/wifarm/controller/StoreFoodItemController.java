package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.FoodItems;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.StoreFoodItem;
import com.agrodev.wifarm.service.StoreFoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fooditem/")
public class StoreFoodItemController {
    @Autowired
    private StoreFoodItemService foodItemService;

    @PostMapping("/createfooditems")
    public ResponseEntity<StandardResponse> createFoodItem(@RequestBody StoreFoodItem foodItems){
        return foodItemService.createFoodItem(foodItems);
    }

    @GetMapping("/getallfooditems")
    public ResponseEntity<StandardResponse> getAll(){
        return foodItemService.getAllFoodItems();
    }
    @GetMapping("/getfooditem")
    public ResponseEntity<StandardResponse> getFoodItem(@RequestParam("id") Long id){
        return foodItemService.getFoodItem(id);
    }
    @PutMapping("/updatefooditem")
    public ResponseEntity<StandardResponse> updateFoodItem(@RequestBody StoreFoodItem foodItems){
        return foodItemService.updateFoodItem(foodItems);
    }
    @DeleteMapping("/deleteallfooditems")
    public ResponseEntity<StandardResponse> deleteAll(){
        return foodItemService.deleteAllFoodItems();
    }
    @DeleteMapping("/deletefooditem")
    public ResponseEntity<StandardResponse> deleteFoodItem(@RequestParam("id") Long id){
        return foodItemService.deleteFoodItem(id);
    }
}
