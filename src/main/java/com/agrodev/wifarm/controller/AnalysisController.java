package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.FoodAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analysis/")
public class AnalysisController {
    @Autowired
    private FoodAnalysis foodAnalysis;

    @GetMapping("/getmealsfortheday")
    public ResponseEntity<StandardResponse> getMealsForDay(@RequestParam("mealDay") String mealDay){
        return foodAnalysis.getMealsForTheDay(mealDay);
    }

    @GetMapping("/getmealsformealtime")
    public ResponseEntity<StandardResponse> getMealsForMealTime(@RequestParam("weekDay") String weekDay, @RequestParam("mealTime") String mealTime){
        return foodAnalysis.getMealsForTheTime(weekDay, mealTime);
    }
}
