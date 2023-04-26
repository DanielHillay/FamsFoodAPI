package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class MealRequest {
    private String orderId;
    private String mealId;
    private Map<String, Long> foodItems;
}
