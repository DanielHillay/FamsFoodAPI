package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Crops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cropId;
    private String cropCategory;
    private String cropName;
    private Double cropEstimatedDuration;
    private Double cropActualDuration;
    private Date plantDate;
    private int amountPlanted;
    private boolean isPlanted;
    private boolean isHarvested;
    private Double price;
    private Date harvestDate;
    private double dailyInterestRate;
    private double monthlyInterestRate;
    private double estimatedYeildRate;
    @ManyToMany(mappedBy = "cropsList")
    private List<Farm> farms;
}
