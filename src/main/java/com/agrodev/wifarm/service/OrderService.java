package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                mealOrder.setUserAddress(mealOrders.getAddress());
                mealOrder.setDelivered(false);
                mealOrder.setAttendedTo(false);


                orderRepository.save(mealOrder);
                return StandardResponse.sendHttpResponse(true, "Successful", mealOrder);
            }else{
                for(MealOrder ords : mealOrders.getOrderList()){
                    ords.setOrderId(String.format("%08d", customerRand.nextInt(100000000)));
                    ords.setOrderDate(new Date());
                    ords.setOrderTime(LocalDateTime.now());
                    ords.setUserAddress(mealOrders.getAddress());
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

    public ResponseEntity<StandardResponse> getPendingOrders() {
        try {
            List<MealOrder> orderList = orderRepository.findByIsAttendedTo(false);
            return StandardResponse.sendHttpResponse(true, "Successful", orderList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get pending orders");
        }
    }
    public ResponseEntity<StandardResponse> getFulfilledOrders() {
        try {
            List<MealOrder> orderList = orderRepository.findByIsAttendedTo(true);
            return StandardResponse.sendHttpResponse(true, "Successful", orderList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get pending orders");
        }
    }

    public ResponseEntity<StandardResponse> getUserFulfilledOrders(String userId) {
        try {
            List<MealOrder> orderList = new ArrayList<>();
            List<MealOrder> orders = orderRepository.findByUserId(userId);
            for(MealOrder or : orders){
                if(or.isAttendedTo()){
                    orderList.add(or);
                }
            }
            return StandardResponse.sendHttpResponse(true, "Successful", orderList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get fulfilled orders");
        }
    }

    public ResponseEntity<StandardResponse> getUserPendingOrders(String userId) {
        try {
            List<MealOrder> orderList = new ArrayList<>();
            List<MealOrder> orders = orderRepository.findByUserId(userId);
            for(MealOrder or : orders){
                if(!or.isAttendedTo()){
                    orderList.add(or);
                }
            }
            return StandardResponse.sendHttpResponse(true, "Successful", orderList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get fulfilled orders");
        }
    }

    public ResponseEntity<StandardResponse> updateOrder(MealOrder mealOrder) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", orderRepository.save(mealOrder));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update meal");
        }
    }

    public ResponseEntity<StandardResponse> cancelOrder(Long orderId) {
        try {
            MealOrder order = orderRepository.findById(orderId).get();
            orderRepository.delete(order);
            return StandardResponse.sendHttpResponse(true, "Successful", order);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not cancel order");
        }
    }
}
