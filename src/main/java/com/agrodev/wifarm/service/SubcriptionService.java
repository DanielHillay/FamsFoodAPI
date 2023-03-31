package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.Subscription;
import com.agrodev.wifarm.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubcriptionService {
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public ResponseEntity<StandardResponse> createSubPlan(Subscription subscription) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", subscriptionRepo.save(subscription));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> updateSubPlan(Subscription subscription) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", subscriptionRepo.save(subscription));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> getSubPlan(Long subId) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", subscriptionRepo.findById(subId));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> getAllSubPlan() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", subscriptionRepo.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> deleteSubPlan(Long subId) {
        try {
            subscriptionRepo.deleteById(subId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllPlans() {
        try {
            subscriptionRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create sub plan");
        }
    }

    public ResponseEntity<StandardResponse> subscribeToPlan(Long subId) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not subscribe to plan");
        }
    }
}
