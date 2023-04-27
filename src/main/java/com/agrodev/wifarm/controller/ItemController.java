package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.StoreFoodItem;
import com.agrodev.wifarm.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fooditem/")
public class ItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/createfooditem")
    public ResponseEntity<StandardResponse> createFoodItem(@RequestBody StoreFoodItem foodItem){
        return foodItemService.createFoodItem(foodItem);
    }
    @GetMapping("/getfooditems")
    public ResponseEntity<StandardResponse> getFoodItems(){
        return foodItemService.getAllFoodItems();
    }
//    @GetMapping("/getitem")
//    public ResponseEntity<StandardResponse> getFood(@RequestParam("id") Long id){
//        return foodItemService.getFoodItem(id);
//    }
//    @PutMapping("/updatefooditem")
//    public ResponseEntity<StandardResponse> updateFoodItems(@RequestBody StoreFoodItem foodItem){
//        return foodItemService.updateFoodItem(foodItem);
//    }
//    @DeleteMapping("/deletefooditem")
//    public ResponseEntity<StandardResponse> deleteFoodItem(@RequestParam("id") Long id){
//        return foodItemService.deleteFoodItem(id);
//    }
//    @DeleteMapping("/deleteallitems")
//    public ResponseEntity<StandardResponse> deleteAll(){
//        return foodItemService.deleteAllFoodItems();
//    }
}
