package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Data
public class UserMealRequest {
    private String requestId;
    private String userId;
    private String mealName;
    private Map<String, Integer> foodItems;
}
