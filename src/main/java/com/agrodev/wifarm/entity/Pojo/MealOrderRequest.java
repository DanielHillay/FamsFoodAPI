package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.UserMeal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class MealOrderRequest {

    private String mealId;
    private MealOrder mealOrder;
    private String userId;
    private String address;
    private double totalAmount;
    private List<MealOrder> orderList;
}
