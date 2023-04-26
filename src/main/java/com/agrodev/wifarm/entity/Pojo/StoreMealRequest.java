package com.agrodev.wifarm.entity.Pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class StoreMealRequest {
    private String mealName;
    private String mealId;
    private String userId;
    private String description;
    private double mealPrice;
    private double weight;
    private boolean isAvailable;
    private double rating;
    private double quantityAvailable;
    private Map<String, Integer> foodItems;
}
