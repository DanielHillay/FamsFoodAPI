package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.ItemsToPrepare;
import com.agrodev.wifarm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodAnalysis {

    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private FeedingSchemeRepo schemeRepo;
    @Autowired
    private MealTimeRepo mealTimeRepo;
    @Autowired
    private StoreFoodItemRepo storeFoodItemRepo;
    @Autowired
    private UserMealRepository userMealRepository;

    private final static String FOODITEM1 = "RICE";
    private final static String FOODITEM2 = "BEANS";
    private final static String FOODITEM3 = "BEEF";
    private final static String FOODITEM4 = "CHICKEN";
    private final static String FOODITEM5 = "OFADA STEW";
    private final static String FOODITEM6 = "BANGA STEW";
    private final static String FOODITEM7 = "REGULAR STEW";
    private final static String FOODITEM8 = "EGUSI";
    private final static String FOODITEM9 = "EFORIR0";
    private final static String FOODITEM10 = "AFANG";
    private final static String FOODITEM11 = "FISH";



    public List<ItemsToPrepare> getMealsToMakePerMealTime(String weekDay, String mealTime){
        List<StoreFoodItem> item = storeFoodItemRepo.findAll();

        List<ItemsToPrepare> itemsToPrepare = new ArrayList<>();

        List<FeedingScheme> schemeDay = schemeRepo.findByWeekDay(weekDay);
        List<String> mealIds = new ArrayList<>();
        List<FoodItems> rice = new ArrayList<>();
        List<FoodItems> beans = new ArrayList<>();
        List<FoodItems> beef = new ArrayList<>();
        List<FoodItems> chicken = new ArrayList<>();
        List<FoodItems> ofadaStew = new ArrayList<>();
        List<FoodItems> bangaStew = new ArrayList<>();
        List<FoodItems> regularStew = new ArrayList<>();
        List<FoodItems> egusi = new ArrayList<>();
        List<FoodItems> eforiro = new ArrayList<>();
        List<FoodItems> afang = new ArrayList<>();
        List<FoodItems> fish = new ArrayList<>();

        double riceUnits = 0, riceWeight = 0;
        double beansUnits = 0, beansWeight = 0;
        double beefUnits = 0, beefWeight = 0;
        double chickenUnits = 0, chickenWeight = 0;
        double bangaStewUnits = 0, bangaWeight = 0;
        double ofadaStewUnits = 0, ofadaWeight = 0;
        double regularStewUnits = 0, regStewWeight = 0;
        double egusiUnits = 0, egusiWeight = 0;
        double fishUnits = 0, fishWeight = 0;
        double eforiroUnits = 0, eforWeight = 0;
        double afangUnits = 0, afangWeight = 0;

        List<List<MealTiming>> mealTimings = new ArrayList<>();
        for(FeedingScheme schemes : schemeDay){
            mealTimings.add(schemes.getMealTimesList());
        }
        for(List<MealTiming> meals : mealTimings){
            for(MealTiming timings : meals){
                if(timings.getMealTime().equalsIgnoreCase(mealTime)) {
                    mealIds.add(timings.getUserMealId());
                }
            }
        }
        for(String ids : mealIds){
            UserMeal userMeal = userMealRepository.findByMealId(ids).get();
            for(FoodItems items : userMeal.getFoodItems()){
                if(items.getItemName().equalsIgnoreCase(FOODITEM1)){
                    rice.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM2)){
                    beans.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM3)){
                    beef.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM4)){
                    chicken.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM5)){
                    ofadaStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM6)){
                    bangaStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM7)){
                    regularStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM8)){
                    egusi.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM9)){
                    eforiro.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM10)){
                    afang.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM11)){
                    fish.add(items);
                }
            }
        }

        for(FoodItems items : rice){
            riceUnits = riceUnits + items.getUnits();
        }
        for(FoodItems items : beans){
            beansUnits = beansUnits + items.getUnits();
        }
        for(FoodItems items : beef){
            beefUnits = beefUnits + items.getUnits();
        }
        for(FoodItems items : chicken){
            chickenUnits = chickenUnits + items.getUnits();
        }
        for(FoodItems items : bangaStew){
            bangaStewUnits = bangaStewUnits + items.getUnits();
        }
        for(FoodItems items : ofadaStew){
            ofadaStewUnits = ofadaStewUnits + items.getUnits();
        }
        for(FoodItems items : regularStew){
            regularStewUnits = regularStewUnits + items.getUnits();
        }
        for(FoodItems items : egusi){
            egusiUnits = egusiUnits + items.getUnits();
        }
        for(FoodItems items : fish){
            fishUnits = fishUnits + items.getUnits();
        }
        for(FoodItems items : eforiro){
            eforiroUnits = eforiroUnits + items.getUnits();
        }
        for(FoodItems items : afang){
            afangUnits = afangUnits + items.getUnits();
        }

        for(StoreFoodItem foodItem : item){
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM1)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM1.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM1 );
                items.setItemWeight(riceUnits * foodItem.getWeight());
                items.setUnits(riceUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM2)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM2.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM2 );
                items.setItemWeight(beansUnits * foodItem.getWeight());
                items.setUnits(beansUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM3)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM3.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM3 );
                items.setItemWeight(beefUnits * foodItem.getWeight());
                items.setUnits(beefUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM4)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM4.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM4);
                items.setItemWeight(chickenUnits * foodItem.getWeight());
                items.setUnits(chickenUnits);
                itemsToPrepare.add(items);
            }if(foodItem.getItemName().equalsIgnoreCase(FOODITEM5)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM5.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM5 );
                items.setItemWeight(ofadaStewUnits * foodItem.getWeight());
                items.setUnits(ofadaStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM6)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM6.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM6 );
                items.setItemWeight(bangaStewUnits * foodItem.getWeight());
                items.setUnits(bangaStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM7)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM7.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM7);
                items.setItemWeight(regularStewUnits * foodItem.getWeight());
                items.setUnits(regularStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM8)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM8.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM8);
                items.setItemWeight(egusiUnits * foodItem.getWeight());
                items.setUnits(egusiUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM9)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM9.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM9 );
                items.setItemWeight(eforiroUnits * foodItem.getWeight());
                items.setUnits(eforiroUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM10)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM10.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM10 );
                items.setItemWeight(afangUnits * foodItem.getWeight());
                items.setUnits(afangUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM11)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM11.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM11 );
                items.setItemWeight(fishUnits * foodItem.getWeight());
                items.setUnits(fishUnits);
                itemsToPrepare.add(items);
            }

        }
        return itemsToPrepare;
    }

    public List<ItemsToPrepare> getMealsToMakePerDay(String weekDay){
        List<StoreFoodItem> item = storeFoodItemRepo.findAll();

        List<ItemsToPrepare> itemsToPrepare = new ArrayList<>();

        List<FeedingScheme> schemeDay = schemeRepo.findByWeekDay(weekDay);
        List<String> mealIds = new ArrayList<>();
        List<FoodItems> rice = new ArrayList<>();
        List<FoodItems> beans = new ArrayList<>();
        List<FoodItems> beef = new ArrayList<>();
        List<FoodItems> chicken = new ArrayList<>();
        List<FoodItems> ofadaStew = new ArrayList<>();
        List<FoodItems> bangaStew = new ArrayList<>();
        List<FoodItems> regularStew = new ArrayList<>();
        List<FoodItems> egusi = new ArrayList<>();
        List<FoodItems> eforiro = new ArrayList<>();
        List<FoodItems> afang = new ArrayList<>();
        List<FoodItems> fish = new ArrayList<>();

        double riceUnits = 0, riceWeight = 0;
        double beansUnits = 0, beansWeight = 0;
        double beefUnits = 0, beefWeight = 0;
        double chickenUnits = 0, chickenWeight = 0;
        double bangaStewUnits = 0, bangaWeight = 0;
        double ofadaStewUnits = 0, ofadaWeight = 0;
        double regularStewUnits = 0, regStewWeight = 0;
        double egusiUnits = 0, egusiWeight = 0;
        double fishUnits = 0, fishWeight = 0;
        double eforiroUnits = 0, eforWeight = 0;
        double afangUnits = 0, afangWeight = 0;

        List<List<MealTiming>> mealTimings = new ArrayList<>();
        for(FeedingScheme schemes : schemeDay){
            mealTimings.add(schemes.getMealTimesList());
        }
        for(List<MealTiming> meals : mealTimings){
            for(MealTiming timings : meals){
                    mealIds.add(timings.getUserMealId());
            }
        }
        for(String ids : mealIds){
            UserMeal userMeal = userMealRepository.findByMealId(ids).get();
            for(FoodItems items : userMeal.getFoodItems()){
                if(items.getItemName().equalsIgnoreCase(FOODITEM1)){
                    rice.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM2)){
                    beans.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM3)){
                    beef.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM4)){
                    chicken.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM5)){
                    ofadaStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM6)){
                    bangaStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM7)){
                    regularStew.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM8)){
                    egusi.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM9)){
                    eforiro.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM10)){
                    afang.add(items);
                }
                if(items.getItemName().equalsIgnoreCase(FOODITEM11)){
                    fish.add(items);
                }
            }
        }

        for(FoodItems items : rice){
            riceUnits = riceUnits + items.getUnits();
        }
        for(FoodItems items : beans){
            beansUnits = beansUnits + items.getUnits();
        }
        for(FoodItems items : beef){
            beefUnits = beefUnits + items.getUnits();
        }
        for(FoodItems items : chicken){
            chickenUnits = chickenUnits + items.getUnits();
        }
        for(FoodItems items : bangaStew){
            bangaStewUnits = bangaStewUnits + items.getUnits();
        }
        for(FoodItems items : ofadaStew){
            ofadaStewUnits = ofadaStewUnits + items.getUnits();
        }
        for(FoodItems items : regularStew){
            regularStewUnits = regularStewUnits + items.getUnits();
        }
        for(FoodItems items : egusi){
            egusiUnits = egusiUnits + items.getUnits();
        }
        for(FoodItems items : fish){
            fishUnits = fishUnits + items.getUnits();
        }
        for(FoodItems items : eforiro){
            eforiroUnits = eforiroUnits + items.getUnits();
        }
        for(FoodItems items : afang){
            afangUnits = afangUnits + items.getUnits();
        }

        for(StoreFoodItem foodItem : item){
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM1)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM1.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM1 );
                items.setItemWeight(riceUnits * foodItem.getWeight());
                items.setUnits(riceUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM2)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM2.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM2 );
                items.setItemWeight(beansUnits * foodItem.getWeight());
                items.setUnits(beansUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM3)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM3.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM3 );
                items.setItemWeight(beefUnits * foodItem.getWeight());
                items.setUnits(beefUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM4)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM4.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM4);
                items.setItemWeight(chickenUnits * foodItem.getWeight());
                items.setUnits(chickenUnits);
                itemsToPrepare.add(items);
            }if(foodItem.getItemName().equalsIgnoreCase(FOODITEM5)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM5.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM5 );
                items.setItemWeight(ofadaStewUnits * foodItem.getWeight());
                items.setUnits(ofadaStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM6)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM6.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM6 );
                items.setItemWeight(bangaStewUnits * foodItem.getWeight());
                items.setUnits(bangaStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM7)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM7.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM7);
                items.setItemWeight(regularStewUnits * foodItem.getWeight());
                items.setUnits(regularStewUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM8)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM8.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM8);
                items.setItemWeight(egusiUnits * foodItem.getWeight());
                items.setUnits(egusiUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM9)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM9.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM9 );
                items.setItemWeight(eforiroUnits * foodItem.getWeight());
                items.setUnits(eforiroUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM10)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM10.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM10 );
                items.setItemWeight(afangUnits * foodItem.getWeight());
                items.setUnits(afangUnits);
                itemsToPrepare.add(items);
            }
            if(foodItem.getItemName().equalsIgnoreCase(FOODITEM11)){
                ItemsToPrepare items = new ItemsToPrepare();
                items.setItemName(FOODITEM11.equalsIgnoreCase(foodItem.getItemName()) ? foodItem.getItemName() : FOODITEM11 );
                items.setItemWeight(fishUnits * foodItem.getWeight());
                items.setUnits(fishUnits);
                itemsToPrepare.add(items);
            }

        }
        return itemsToPrepare;
    }

    public ResponseEntity<StandardResponse> getMealsForTheDay(String mealDay){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", getMealsToMakePerDay(mealDay));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Could not get meals");
        }
    }

    public ResponseEntity<StandardResponse> getMealsForTheTime(String weekDay, String mealTime){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", getMealsToMakePerMealTime(weekDay, mealTime));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return StandardResponse.sendHttpResponse(false, "Could not get meals for mealTime");
        }
    }
}
