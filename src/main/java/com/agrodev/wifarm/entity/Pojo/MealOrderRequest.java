package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.UserMeal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class MealOrderRequest {

    private String mealId;
    private MealOrder mealOrder;
    private Map<String, Long> foodItems;
    private String userId;
    private String address;
    private double totalAmount;
    private List<MealOrder> orderList;
}
