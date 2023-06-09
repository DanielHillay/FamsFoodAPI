package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class FeedingScheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int day;
    private String weekDay;
    private String schemeId;
    private String scheduleId;
    private double weight;
    private String LGA;
    private Long townId;
    private String state;
    private String country;
    private double percentageCarbohydrate;
    private double percentageProtein;
    private double percentageFat;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "FOOD_TIMES",
            joinColumns = {
                    @JoinColumn(name = "SCHEME_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MEAL_ID")
            }
    )
    private List<MealTiming> mealTimesList;
}
