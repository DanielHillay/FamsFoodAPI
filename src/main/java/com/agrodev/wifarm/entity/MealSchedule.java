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
    private String LGA;
    private String town;
    private String State;
    private String country;
    private int duration;
    private int totalNumberOfMeals;
    private double weight;
    private String userId;
    private boolean byUser;
    private String accountNumber;
}
