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
    private String userId;
    private String description;
    private double mealPrice;
    private double weight;
    private boolean isAvailable;
    private double rating;
    private double quantityAvailable;
    private double percentageCarbohydrate;
    private double percentageProtein;
    private double percentageFat;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "STOREMEAL_ITEMS",
            joinColumns = {
                    @JoinColumn(name = "MEAL_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ITEM_ID")
            }
    )
    private Set<StoreFoodItem> foodItems;
}
