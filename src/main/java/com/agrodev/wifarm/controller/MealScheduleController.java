package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MealSchedule;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.MealScheduleService;
import com.sendgrid.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mealschedule")
public class MealScheduleController {

    @Autowired
    private MealScheduleService mealScheduleService;

    @PostMapping("/createschedule")
    public ResponseEntity<StandardResponse> createSchedule(@RequestBody MealSchedule mealSchedule){
        return mealScheduleService.createSchedule(mealSchedule);
    }
    @PutMapping("/updateschedule")
    public ResponseEntity<StandardResponse> updateSchedule(@RequestBody MealSchedule mealSchedule){
        return mealScheduleService.updateSchedule(mealSchedule);
    }
    @GetMapping("/getschedule")
    public ResponseEntity<StandardResponse> getSchedule(@RequestParam("id") Long id){
        return mealScheduleService.getSchedule(id);
    }
    @GetMapping("/getallschedules")
    public ResponseEntity<StandardResponse> getAll(){
        return mealScheduleService.getAllSchedules();
    }
    @DeleteMapping("/deleteschedule")
    public ResponseEntity<StandardResponse> deleteSchedule(@RequestParam("id") Long id){
        return mealScheduleService.deleteSchedule(id);
    }
    @DeleteMapping("/deleteallschedules")
    public ResponseEntity<StandardResponse> deleteAll(){
        return mealScheduleService.deleteAllSchedules();
    }
}
