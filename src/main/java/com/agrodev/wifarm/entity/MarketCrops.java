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
public class MarketCrops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropCategory;
    private String cropName;
    private Double cropEstimatedDuration;
    private boolean isAvailablle;
    private boolean isPlanted;
    private Date datePlanted;
    private double cropPrice;
    private double accruedAmount;
    private double squareMeters;
    private double dailyInterestRate;
    private double monthlyInterestRate;
    private double lifeCycleYieldRate;
    private String imageUrl;
}
