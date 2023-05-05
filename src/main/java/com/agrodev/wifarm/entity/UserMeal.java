package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class UserMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealName;
    private String mealId;
    private String userId;
    private double mealPrice;
    private String LGA;
    private String town;
    private String State;
    private String country;
    private double weight;
    private boolean isFavourite;
    private double percentageCarbohydrate;
    private double percentageProtein;
    private double percentageFat;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USERMEAL_ITEMS",
            joinColumns = {
                    @JoinColumn(name = "MEALL_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ITEMS_ID")
            }
    )
    private Set<FoodItems> foodItems;
}
