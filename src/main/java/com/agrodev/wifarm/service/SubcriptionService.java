package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Account;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.Subscription;
import com.agrodev.wifarm.repository.AccountRepository;
import com.agrodev.wifarm.repository.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SubcriptionService {
    @Autowired
    private SubscriptionRepo subscriptionRepo;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UpdateService updateService;

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

    public ResponseEntity<StandardResponse> subscribeToPlan(String userId, Long subId, String scheduleId) {
        try {
            Account account = accountRepository.findByUserId(userId).get();
            Subscription sub = subscriptionRepo.findById(subId).get();
            if(!account.getSubName().isEmpty()){
                return StandardResponse.sendHttpResponse(true, "This account is already subscribed to a plan");
            }else {
                account.setSubName(sub.getSubName());
                account.setSubDate(new Date());
                account.setSubTime(LocalDateTime.now());
                account.setScheduleId(scheduleId);

                accountRepository.save(account);
                updateService.endSubscription(account.getAccountId(), sub.getDays());
                return StandardResponse.sendHttpResponse(true, "Successful");
            }
        }catch (Exception e){
            return StandardResponse.sendHttpResponse(false, "Could not subscribe to plan");
        }
    }

    public ResponseEntity<StandardResponse> unSubscribeToPlan(Long accountId){
        try {
            removeSubFromAccount(accountId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not unsubscribe from plan");
        }
    }

    public void removeSubFromAccount(Long accountId){
        Account account = accountRepository.findById(accountId).get();
        if(!account.getSubName().isEmpty()){
            account.setSubName("");
            account.setSubDate(null);
            account.setSubTime(null);
            account.setScheduleId("");
            account.setKiloGrams(0);
            account.setSubscriptionType("");
            accountRepository.save(account);
        }
    }
}
