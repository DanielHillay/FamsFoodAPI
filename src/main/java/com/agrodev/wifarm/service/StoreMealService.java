package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.StoreMeal;
import com.agrodev.wifarm.repository.StoreMealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StoreMealService {
    @Autowired
    private StoreMealRepo storeMealRepo;

    public ResponseEntity<StandardResponse> createStoreMeal(StoreMeal storeMeal) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.save(storeMeal));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> updateStoreMeal(StoreMeal storeMeal) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.save(storeMeal));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> getStoreMeal(Long id) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.findById(id));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> getAllStoreMeals() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", storeMealRepo.findAll());
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> deleteStoreMeal(Long id) {
        try {
            storeMealRepo.deleteById(id);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }

    public ResponseEntity<StandardResponse> deleteAllStoreMeals() {
        try {
            storeMealRepo.deleteAll();
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create store meal");
        }
    }
}
