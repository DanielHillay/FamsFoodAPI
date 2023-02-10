package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.entity.User;
import com.agrodev.wifarm.repository.FarmRepository;
import com.agrodev.wifarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<StandardResponse> createFarm(Farm farm) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", farmRepository.save(farm));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create farm");
        }
    }

    public ResponseEntity<StandardResponse> updateFarm(Farm farm) {
        try {
            return StandardResponse.sendHttpResponse(true, "Successful", farmRepository.save(farm));
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update Farm");
        }
    }

    public ResponseEntity<StandardResponse> createFarmForCustomer(Farm farm, Long userId) {
        try {
            User user = userRepository.findById(userId).get();
            farm.setCustomerId(user.getUserId());
            return StandardResponse.sendHttpResponse(true, "Successful", farm);
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create Farm for customer");
        }
    }

    public ResponseEntity<StandardResponse> getFarmByCustomerId(String customerId) {
        try {
            List<Farm> farmList = farmRepository.findByCustomerId(customerId);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get user FARMS");
        }
    }

    public ResponseEntity<StandardResponse> updateFarmForUser(Farm farm, Long userId) {
        try {
            List<Farm> farmList = farmRepository.findByCustomerId(userRepository.findById(userId).get().getUserId());
            for(Farm fa : farmList){
                if(fa.equals(farm)){
                    farmRepository.save(fa);
                }
            }
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update farm for user");
        }
    }
}
