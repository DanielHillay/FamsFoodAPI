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
    private String day;
    private String scheduleName;
    private String duration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USERMEAL_SCHEDULE",
            joinColumns = {
                    @JoinColumn(name = "SCHEDULE_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MEAL_ID")
            }
    )
    private Set<UserMeal> userMeals;
}
