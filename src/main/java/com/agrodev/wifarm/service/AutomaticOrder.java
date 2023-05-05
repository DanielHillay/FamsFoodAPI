package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.MealOrderRequest;
import com.agrodev.wifarm.repository.FeedingSchemeRepo;
import com.agrodev.wifarm.repository.OrderRepository;
import com.agrodev.wifarm.repository.UserMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class AutomaticOrder {

    @Autowired
    private FeedingSchemeRepo feedingSchemeRepo;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserMealRepository userMealRepository;

    private static final String MEALTIME1 = "BREAKFAST";
    private static final String MEALTIME2 = "LUNCH";
    private static final String MEALTIME3 = "DINNER";
;


    @Scheduled(cron = "30 5 ? * MON-SUN")
    public void orderBreakFast(){
        String day = LocalDate.now().getDayOfWeek().name();
        List<FeedingScheme> schemes = feedingSchemeRepo.findByWeekDay(day);
        List<List<MealTiming>> timings = new ArrayList<>();
        List<String> mealIds = new ArrayList<>();

        Random customRand = new Random();
        MealOrderRequest orderRequest = new MealOrderRequest();

        for(FeedingScheme scheme : schemes){
            timings.add(scheme.getMealTimesList());

        }

        for(List<MealTiming> meals : timings){
            for(MealTiming timing : meals){
                if(timing.getMealTime().equalsIgnoreCase(MEALTIME1)) {
                    mealIds.add(timing.getUserMealId());
                }
            }
        }

        for(String mealId : mealIds){
            MealOrder mealOrder = new MealOrder();
            mealOrder.setMealId(mealId);
            mealOrder.setOrderId(String.format("%08d", customRand.nextInt(100000000)));
            mealOrder.setOrderDate(new Date());
            mealOrder.setOrderTime(LocalDateTime.now());
            mealOrder.setDelivered(false);
            mealOrder.setAttendedTo(false);

            orderRequest.getOrderList().add(orderRepository.save(mealOrder));
        }

        addLocationToOrders(orderRequest.getOrderList());
    }

    @Scheduled(cron = "30 11 ? * MON-SUN")
    public void orderLunchMeal(){
        String day = LocalDate.now().getDayOfWeek().name();
        List<FeedingScheme> schemes = feedingSchemeRepo.findByWeekDay(day);
        List<List<MealTiming>> timings = new ArrayList<>();
        List<String> mealIds = new ArrayList<>();

        Random customRand = new Random();
        MealOrderRequest orderRequest = new MealOrderRequest();

        for(FeedingScheme scheme : schemes){
            timings.add(scheme.getMealTimesList());

        }

        for(List<MealTiming> meals : timings){
            for(MealTiming timing : meals){
                if(timing.getMealTime().equalsIgnoreCase(MEALTIME2)) {
                    mealIds.add(timing.getUserMealId());
                }
            }
        }

        for(String mealId : mealIds){
            MealOrder mealOrder = new MealOrder();
            mealOrder.setMealId(mealId);
            mealOrder.setOrderId(String.format("%08d", customRand.nextInt(100000000)));
            mealOrder.setOrderDate(new Date());
            mealOrder.setOrderTime(LocalDateTime.now());
            mealOrder.setDelivered(false);
            mealOrder.setAttendedTo(false);

            orderRequest.getOrderList().add(orderRepository.save(mealOrder));
        }

        addLocationToOrders(orderRequest.getOrderList());
    }

    @Scheduled(cron = "30 16 ? * MON-SUN")
    public void orderDinner(){
        String day = LocalDate.now().getDayOfWeek().name();
        List<FeedingScheme> schemes = feedingSchemeRepo.findByWeekDay(day);
        List<List<MealTiming>> timings = new ArrayList<>();
        List<String> mealIds = new ArrayList<>();

        Random customRand = new Random();
        MealOrderRequest orderRequest = new MealOrderRequest();

        for(FeedingScheme scheme : schemes){
            timings.add(scheme.getMealTimesList());

        }

        for(List<MealTiming> meals : timings){
            for(MealTiming timing : meals){
                if(timing.getMealTime().equalsIgnoreCase(MEALTIME3)) {
                    mealIds.add(timing.getUserMealId());
                }
            }
        }

        for(String mealId : mealIds){
            MealOrder mealOrder = new MealOrder();
            mealOrder.setMealId(mealId);
            mealOrder.setOrderId(String.format("%08d", customRand.nextInt(100000000)));
            mealOrder.setOrderDate(new Date());
            mealOrder.setOrderTime(LocalDateTime.now());
            mealOrder.setDelivered(false);
            mealOrder.setAttendedTo(false);

            orderRequest.getOrderList().add(orderRepository.save(mealOrder));
        }

        addLocationToOrders(orderRequest.getOrderList());
    }

    public void addLocationToOrders(List<MealOrder> orders){
        for(MealOrder order : orders){
            UserMeal meal = userMealRepository.findByMealId(order.getMealId()).get();
            order.setLGA(meal.getLGA());
            order.setState(meal.getState());
            order.setCountry(meal.getCountry());
            order.setTown(meal.getTown());

            orderRepository.save(order);
        }

    }
}
