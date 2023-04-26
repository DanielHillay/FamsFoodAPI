package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class MealSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheduleId;
    private String scheduleName;
    private int duration;
    private int totalNumberOfMeals;
    private double weight;
    private String accountNumber;
    private String userCode;
}
