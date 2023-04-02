package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.StoreMeal;
import com.agrodev.wifarm.service.StoreMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal/")
public class StoreMealController {
    @Autowired
    private StoreMealService mealService;

    @PostMapping("/createmeal")
    public ResponseEntity<StandardResponse> createStoreMeal(@RequestBody StoreMeal storeMeal){
        return mealService.createStoreMeal(storeMeal);
    }
    @PutMapping("/updatemeal")
    public ResponseEntity<StandardResponse> updateMeal(@RequestBody StoreMeal storeMeal){
        return mealService.updateStoreMeal(storeMeal);
    }
    @GetMapping("/getmeal")
    public ResponseEntity<StandardResponse> getStoreMeal(@RequestParam("id") Long id){
        return mealService.getStoreMeal(id);
    }
    @GetMapping("/getallmeals")
    public ResponseEntity<StandardResponse> getAllMeals(){
        return mealService.getAllStoreMeals();
    }
    @DeleteMapping("/deletestoremeal")
    public ResponseEntity<StandardResponse> deleteStoreMeal(@RequestParam("id") Long id){
        return mealService.deleteStoreMeal(id);
    }
    @DeleteMapping("/deleteallmeals")
    public ResponseEntity<StandardResponse> deleteAllStoreMeals(){
        return mealService.deleteAllStoreMeals();
    }
} 
