package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.entity.UserMeal;
import com.agrodev.wifarm.repository.OrderRepository;
import com.agrodev.wifarm.repository.UserMealRepository;
import com.agrodev.wifarm.repository.UserRepository;
import com.agrodev.wifarm.util.CodeGenerator;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMealRepository userMealRepository;
    @Autowired
    private UpdateService updateService;

    private final String filter1 = "STATE";
    private final String filter2 = "LGA";
    private final String filter3 = "TOWN";
    private final String filter4 = "COUNTRY";
    public ResponseEntity<StandardResponse> submitOrder(MealOrderRequest mealOrders) {
        try {
            Random customerRand = new Random();
            if(mealOrders.getOrderList().isEmpty()) {

                MealOrder mealOrder = mealOrders.getMealOrder();
                User user = userRepository.findByUserId(mealOrder.getUserId()).get();

                mealOrder.setOrderId(String.format("%08d", customerRand.nextInt(100000000)));
                mealOrder.setOrderDate(new Date());
                mealOrder.setOrderTime(LocalDateTime.now());
                mealOrder.setUserAddress(user.getUserAddress());
                mealOrder.setDelivered(false);
                mealOrder.setAttendedTo(false);
                mealOrder.setLGA(user.getLGA());
                mealOrder.setState(user.getState());
                mealOrder.setCountry(user.getCountry());
                mealOrder.setTown(user.getTown());

                orderRepository.save(mealOrder);
                return StandardResponse.sendHttpResponse(true, "Successful", mealOrder);
            }else{
                for(MealOrder ords : mealOrders.getOrderList()){
                    User user = userRepository.findByUserId(mealOrders.getUserId()).get();

                    ords.setOrderId(String.format("%08d", customerRand.nextInt(100000000)));
                    ords.setOrderDate(new Date());
                    ords.setOrderTime(LocalDateTime.now());
                    ords.setUserAddress(user.getUserAddress());
                    ords.setDelivered(false);
                    ords.setAttendedTo(false);
                    ords.setLGA(user.getLGA());
                    ords.setState(user.getState());
                    ords.setCountry(user.getCountry());
                    ords.setTown(user.getTown());

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
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findByUserName(authentication.getName()).get();

            List<MealOrder> orderList = orderRepository.findByIsAttendedToAndTown(false, user.getTown());
            return StandardResponse.sendHttpResponse(true, "Successful", orderList);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get pending orders");
        }
    }

    public ResponseEntity<StandardResponse> getPendingOrdersByTown(String town){
        try {
            List<MealOrder> orders = orderRepository.findByIsAttendedToAndTown(false, town);
            return StandardResponse.sendHttpResponse(true, "Successful", orders);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get orders by town");
        }
    }

    public ResponseEntity<StandardResponse> getPendingOrdersByFilter(String filterName, String filter) {
        try {
            List<MealOrder> orderList = new ArrayList<>();
            if(filterName.equalsIgnoreCase(filter1)){
                orderList = orderRepository.findByIsAttendedToAndState(false, filter);
            }
            if(filterName.equalsIgnoreCase(filter2)){
                orderList = orderRepository.findByIsAttendedToAndLGA(false, filter);
            }
            if(filterName.equalsIgnoreCase(filter3)){
                orderList = orderRepository.findByIsAttendedToAndTown(false, filter);
            }
            if(filterName.equalsIgnoreCase(filter4)){
                orderList = orderRepository.findByIsAttendedToAndCountry(false, filter);
            }
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

    public ResponseEntity<StandardResponse> getFulfilledOrdersByFilter(String filterName, String filter) {
        try {
            List<MealOrder> orderList = new ArrayList<>();
            if(filterName.equalsIgnoreCase(filter1)){
                orderList = orderRepository.findByIsAttendedToAndState(true, filter);
            }
            if(filterName.equalsIgnoreCase(filter2)){
                orderList = orderRepository.findByIsAttendedToAndLGA(true, filter);
            }
            if(filterName.equalsIgnoreCase(filter3)){
                orderList = orderRepository.findByIsAttendedToAndTown(true, filter);
            }
            if(filterName.equalsIgnoreCase(filter4)){
                orderList = orderRepository.findByIsAttendedToAndCountry(true, filter);
            }
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

    public ResponseEntity<StandardResponse> getPendingAllOrders() {
        try {
            List<MealOrder> orderList = orderRepository.findByIsAttendedTo(false);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all pending orders");
        }
    }
}
