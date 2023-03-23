package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.TradeCropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tradecrops")
public class TradeCropController {

    @Autowired
    private TradeCropService tradeCropService;
    @GetMapping("/getalltradecrops")
    public ResponseEntity<StandardResponse> getAllTradeCrops(){
        return tradeCropService.getAllTradeCrops();
    }

    @GetMapping("/cropsbylocation")
    public ResponseEntity<StandardResponse> getCropsByLocation(@RequestParam("location") String location){
        return tradeCropService.getCropsByLocation(location);
    }

    @GetMapping("/cropsbypricerange")
    public ResponseEntity<StandardResponse> getCropsByLocation(@RequestParam("price") double price){
        return tradeCropService.getCropsByPrice(price);
    }
}
