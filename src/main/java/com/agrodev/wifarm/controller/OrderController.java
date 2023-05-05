package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.OrderService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submitorder")
    public ResponseEntity<StandardResponse> submitOrder(@RequestBody MealOrderRequest mealOrder){
        return orderService.submitOrder(mealOrder);
    }
    @GetMapping("/getallpendingorders")
    public ResponseEntity<StandardResponse> getPendingOrders(){
        return orderService.getPendingAllOrders();
    }

    @GetMapping("/getallfulfilledorders")
    public ResponseEntity<StandardResponse> getFulfilledOrders(){
        return orderService.getFulfilledOrders();
    }
    @GetMapping("/getuserfulfilledorders")
    public ResponseEntity<StandardResponse> getUserFulfilledOrders(@RequestParam("userId") String userId){
        return orderService.getUserFulfilledOrders(userId);
    }
    @GetMapping("/getuserpendingorders")
    public ResponseEntity<StandardResponse> getUserPendingOrders(@RequestParam("userId") String userId){
        return orderService.getUserPendingOrders(userId);
    }
    @PutMapping("/updateorder")
    public ResponseEntity<StandardResponse> updateOrder(@RequestBody MealOrder mealOrder){
        return orderService.updateOrder(mealOrder);
    }
    @PostMapping("/cancelorder")
    public ResponseEntity<StandardResponse> cancelOrder(@RequestParam("orderId") Long orderId){
        return orderService.cancelOrder(orderId);
    }
}
