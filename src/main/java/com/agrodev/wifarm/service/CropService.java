package com.agrodev.wifarm.service;

import com.agrodev.wifarm.entity.Crops;
import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.repository.CropRepository;
import com.agrodev.wifarm.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CropService {
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private FarmRepository farmRepository;

    public ResponseEntity<StandardResponse> addExistingCropToFarm(Crops crops, Long farmId) {
        try {
            Farm farm = farmRepository.findById(farmId).get();
            for(Crops cr : farm.getCropsList()){
                if(cr.getCropName().equalsIgnoreCase(crops.getCropName())){
                    cr.setAmountPlanted(crops.getAmountPlanted());
                    cr.setPrincipalAmount(cr.getPrincipalAmount() + (cr.getAmountPlanted() * cr.getPrice()));
                    cropRepository.save(cr);
                }
            }
            farmRepository.save(farm);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add crop to farm land");
        }
    }

    public ResponseEntity<StandardResponse> addCropToFarm(MarketCrops marketCrop, Long farmId){
        try {
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not add crop to farm");
        }
    }
}
