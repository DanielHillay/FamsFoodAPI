package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@Data
public class TradeCrops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropName;
    private String cropCategory;
    private double sellingPrice;
    private String cropLocation;
    private String LGA;
    private double numberToSell;
    private double accruedAmount;
    private Date harvestDate;
    private double dailyInterest;
    private double daysLeft;
    private double monthlyInterest;
    private String sellerId;
    private String cropId;
}
