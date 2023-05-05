package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.*;
import com.agrodev.wifarm.entity.Pojo.ScheduleRequest;
import com.agrodev.wifarm.entity.Pojo.ScheduleResponse;
import com.agrodev.wifarm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MealScheduleService {
    @Autowired
    private MealScheduleRepo mealScheduleRepo;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FeedingSchemeRepo feedingSchemeRepo;
    @Autowired
    private SubscriptionRepo subscriptionRepo;
    @Autowired
    private UserMealRepository userMealRepo;
    @Autowired
    private MealTimeRepo mealTimeRepo;

    public ResponseEntity<StandardResponse> createSchedule(ScheduleRequest request) {
        try {
            Random customRand = new Random();
            MealSchedule mealSchedule = request.getSchedule();
            mealSchedule.setScheduleId(String.format(String.format("%04d", customRand.nextInt(10000))));
            int days = request.getSchedule().getDuration();
            double scheduleWeight = 0;
            for(int i = 0; i<=days; i++){
                double schemeWeight = 0;
                FeedingScheme scheme = new FeedingScheme();
//                scheme.setMealTimesList(request.getTimeList().get(i));
                scheme.setScheduleId(mealSchedule.getScheduleId());
                scheme.setDay(i);
                scheme.setSchemeId(String.format(String.format("%04d", customRand.nextInt(10000))));
                for(MealTiming times : request.getTimeList().get(i)){
                    times.setFeedingSchemeId(scheme.getSchemeId());
                    scheme.setWeekDay(times.getWeekDay());
                    scheme.getMealTimesList().add(mealTimeRepo.save(times));
                    double weight = userMealRepo.findByMealId(times.getUserMealId()).get().getWeight();
                    schemeWeight = schemeWeight + weight;
                }
                scheme.setWeight(schemeWeight);
                feedingSchemeRepo.save(scheme);
                scheduleWeight = scheduleWeight + schemeWeight;
            }


            return StandardResponse.sendHttpResponse(true, "Successful", mealSchedule);
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> addScheduleToSubscription(Long scheduleId, Long subId, String userCode){
        try {
            Subscription subscription = subscriptionRepo.findById(subId).get();
            MealSchedule schedule = mealScheduleRepo.findById(scheduleId).get();

            if(schedule.getTotalNumberOfMeals() > subscription.getNumberOfMeals() || Double.compare(schedule.getWeight(), subscription.getWeight()) > 0){
                return StandardResponse.sendHttpResponse(true, "Sorry, you cannot use this schedule for " +
                        "this plan because the number of meals/meal types exceed the sub limit. Kindly upgrade your plan to enjoy more.");
            }else{
                accountRepository.findByUserId(userCode).get().setScheduleId(schedule.getScheduleId());
            }
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add schedule to sub");
        }
    }

    public ResponseEntity<StandardResponse> updateSchedule(MealSchedule mealSchedule) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", mealScheduleRepo.save(mealSchedule));
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> getAllSchedules() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", mealScheduleRepo.findAll());
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> getSchedule(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", mealScheduleRepo.findById(id));
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> getUserSchedule(String userId){
        try {
//            List<MealSchedule> scheduleList = mealScheduleRepo.findAllByUserId(userId);
            MealSchedule schedule = mealScheduleRepo.findByUserId(userId).get();
            List<FeedingScheme> days = feedingSchemeRepo.findAllByScheduleId(schedule.getScheduleId());
            ScheduleResponse response = new ScheduleResponse();
            response.setMealSchedule(schedule);
            response.setUserId(userId);
            response.setDays(days);
            return StandardResponse.sendHttpResponse(true, "Successful", response);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not return schedule");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllSchedules() {
        try {
            mealScheduleRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> deleteSchedule(Long id) {
        try {
            mealScheduleRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
        }
    }

    public ResponseEntity<StandardResponse> registerSchedule(MealSchedule mealSchedule) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", mealScheduleRepo.save(mealSchedule));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not register Schedule");
        }
    }
}
