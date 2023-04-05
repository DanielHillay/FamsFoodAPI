package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
public class MealOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private String userId;
    private String mealName;
    private String mealId;
    private double mealWeight;
    private double mealPrice;
    private boolean isDelivered;
    private String userAddress;
    private Date orderDate;
    private boolean isAttendedTo;
    private LocalDateTime orderTime;

}
