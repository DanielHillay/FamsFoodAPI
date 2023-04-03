package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.StoreMeal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class AdminDashBoard {

    private String adminId;
    private double numberOfOrders;
    private double numberOfSubscribers;
    private double totalRevenue;
    private double totalOrdersInKg;
    private double pendingOrders;
    private List<StoreMeal> storeMeals;

}
