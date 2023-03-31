package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.Subscription;
import com.agrodev.wifarm.service.SubcriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription/")
public class SubscriptionController {
    @Autowired
    private SubcriptionService subscriptionService;

    @PostMapping("/createsubplan")
    public ResponseEntity<StandardResponse> createSubPlan(@RequestBody Subscription subscription){
        return subscriptionService.createSubPlan(subscription);
    }

    @GetMapping("/getsubplan")
    public ResponseEntity<StandardResponse> getSubPlan(@RequestParam("id") Long id){
        return subscriptionService.getSubPlan(id);
    }

    @GetMapping("/getallsubplans")
    public ResponseEntity<StandardResponse> getAllSubPlans(){
        return subscriptionService.getAllSubPlan();
    }
    @PutMapping("/updatesubplan")
    public ResponseEntity<StandardResponse> updateSubPlan(@RequestBody Subscription subscription){
        return subscriptionService.updateSubPlan(subscription);
    }
    @PostMapping("/subscribe")
    public ResponseEntity<StandardResponse> subscribeToPlan(@RequestParam("subId") Long subId){
        return subscriptionService.subscribeToPlan(subId);
    }
    @DeleteMapping("/deletesubplan")
    public ResponseEntity<StandardResponse> deleteSubPlan(@RequestParam("id") Long id){
        return subscriptionService.deleteSubPlan(id);
    }
    @DeleteMapping("/deleteallsubplans")
    public ResponseEntity<StandardResponse> deleteAll(){
        return subscriptionService.deleteAllPlans();
    }

}
