package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submitorder")
    public ResponseEntity<StandardResponse> submitOrder(@RequestBody MealOrderRequest mealOrder){
        return orderService.submitOrder(mealOrder);
    }
}
