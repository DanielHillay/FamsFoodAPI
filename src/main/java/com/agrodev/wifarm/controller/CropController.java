package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Crops;
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

    @PostMapping("/createcrop")
    public ResponseEntity<StandardResponse> createCrop(@RequestBody Crops crops, @RequestParam("farmId") Long farmId){
        return cropService.createCropForCustomer(crops, farmId);
    }

//    @PostMapping("/addcroptofarm")
//    public ResponseEntity<StandardResponse> addCropToFarm(@RequestParam("cropName") String cropName, @RequestParam("farmId") Long farmId){
//        return cropService.addCropToFarm(cropName, farmId);
//    }
}
