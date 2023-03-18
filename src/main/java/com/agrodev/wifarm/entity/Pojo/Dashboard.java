package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.Crops;
import com.agrodev.wifarm.entity.Farm;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class Dashboard {

    private Double totalSquareMeters;
    private Farm farm;
    private double numOfCrops;
    private Double principalAmount;
    private Double accuredAmount;
    private int monthsLeft;
    private int daysLeft;
    private int yearsLeft;
    private Set<Crops> cropsList;

}
