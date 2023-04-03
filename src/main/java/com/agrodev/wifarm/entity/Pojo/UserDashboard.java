package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.StoreMeal;
import com.agrodev.wifarm.entity.UserMeal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Data
public class UserDashboard {
    private Long accountId;
    private String userId;
    private double totalKg;
    private List<StoreMeal> storeMeals;
    private List<UserMeal> userMeals;
    private String subscriptionType;
    private String subName;
    private Date subEndDate;
}
