package com.agrodev.wifarm.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
public class StoreMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealName;
    private String mealId;
    private String description;
    private double mealPrice;
    private boolean isAvailable;
    private double quantityAvailable;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "MEAL_ITEMS",
            joinColumns = {
                    @JoinColumn(name = "MEAL_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ITEM_ID")
            }
    )
    private Set<FoodItems> foodItems;
}
