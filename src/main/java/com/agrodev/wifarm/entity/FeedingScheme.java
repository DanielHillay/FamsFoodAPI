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
    private String schemeId;
    private String scheduleId;
    private double weight;
    private double percentageCarbohydrate;
    private double percentageProtein;
    private double percentageFat;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "MEAL_TIME",
            joinColumns = {
                    @JoinColumn(name = "SCHEME_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MEAL_ID")
            }
    )
    private List<MealTime> mealTimesList;
}
