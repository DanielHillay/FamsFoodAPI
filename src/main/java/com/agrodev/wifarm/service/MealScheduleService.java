package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.MealSchedule;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.MealScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MealScheduleService {
    @Autowired
    private MealScheduleRepo mealScheduleRepo;

    public ResponseEntity<StandardResponse> createSchedule(MealSchedule mealSchedule) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", mealScheduleRepo.save(mealSchedule));
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not create schedule");
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
}
