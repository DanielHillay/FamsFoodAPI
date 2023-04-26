package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Pojo.UserMealRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.UserMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usermeal/")
public class UserMealController {
    @Autowired
    private UserMealService userMealService;

    @PostMapping("/createusermeal")
    public ResponseEntity<StandardResponse> createMeal(@RequestBody UserMealRequest request){
        return userMealService.createUserMeal(request);
    }
}

