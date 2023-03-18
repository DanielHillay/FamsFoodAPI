package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Crops;
import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crop")
public class CropController {
    @Autowired
    private CropService cropService;

    @PostMapping("/addcroptofarm")
    public ResponseEntity<StandardResponse> addCrop(@RequestBody MarketCrops crops, @RequestParam("farmId") Long farmId){
        return cropService.addCropToFarm(crops, farmId);
    }


//    @PostMapping("/addexistingcroptofarm")
//    public ResponseEntity<StandardResponse> addExistingCropToFarm(@RequestParam("cropName") String cropName, @RequestParam("farmId") Long farmId){
//        return cropService.addExistingCropToFarm(cropName, farmId);
//    }
}
