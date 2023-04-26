package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.FoodItems;
import com.agrodev.wifarm.entity.MealOrder;
import com.agrodev.wifarm.entity.StoreFoodItem;
import com.agrodev.wifarm.entity.UserMeal;
import com.agrodev.wifarm.repository.FoodItemRepository;
import com.agrodev.wifarm.repository.StoreFoodItemRepo;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UpdateService {

    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private SubcriptionService subcriptionService;
    @Autowired
    private StoreFoodItemRepo foodItemRepository;
    public void endSubscription(Long accountId, Long days) {
        jobScheduler.schedule(LocalDateTime.now().plusHours(days), () -> subcriptionService.removeSubFromAccount(accountId));
    }

    public void subtractFoodItemWeight(UserMeal userMeal){
        for(FoodItems items : userMeal.getFoodItems()) {
            StoreFoodItem foodItems = foodItemRepository.findByItemName(items.getItemName()).get();
            foodItems.setQuantityAvailableInKG(foodItems.getQuantityAvailableInKG() - (items.getUnits() * items.getWeight()));
            foodItems.setUnits(foodItems.getUnits() - items.getUnits());
        }
    }

}
