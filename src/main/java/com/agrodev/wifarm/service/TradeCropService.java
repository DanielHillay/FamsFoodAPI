package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.TradeCropsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TradeCropService {
    @Autowired
    private TradeCropsRepo tradeCropsRepo;

    public ResponseEntity<StandardResponse> getAllTradeCrops() {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get all crops");
        }
    }

    public ResponseEntity<StandardResponse> getCropsByLocation(String location) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get crops");
        }
    }

    public ResponseEntity<StandardResponse> getCropsByPrice(double price) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get crops");
        }
    }
}
