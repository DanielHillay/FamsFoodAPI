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

    private MealOrder mealOrder;
    private String userId;
    private List<MealOrder> orderList;
}
