package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public ResponseEntity<StandardResponse> submitOrder(MealOrderRequest mealOrders) {
        try {
            Random customerRand = new Random();
            if(mealOrders.getOrderList().isEmpty()) {
                MealOrder mealOrder = new MealOrder();
                mealOrder.setOrderId(String.format("%08d", customerRand.nextInt(100000000)));
                mealOrder.setOrderDate(new Date());
                mealOrder.setOrderTime(LocalDateTime.now());
                mealOrder.setDelivered(false);
                mealOrder.setAttendedTo(false);

                orderRepository.save(mealOrder);
                return StandardResponse.sendHttpResponse(true, "Successful", mealOrder);
            }else{
                for(MealOrder ords : mealOrders.getOrderList()){
                    ords.setOrderId(String.format("%08d", customerRand.nextInt(100000000)));
                    ords.setOrderDate(new Date());
                    ords.setOrderTime(LocalDateTime.now());
                    ords.setDelivered(false);
                    ords.setAttendedTo(false);

                    orderRepository.save(ords);
                }
                return StandardResponse.sendHttpResponse(true, "Successful", mealOrders);
            }
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not submit order");
        }
    }
}
