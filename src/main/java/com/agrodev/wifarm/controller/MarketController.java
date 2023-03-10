package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @PutMapping("/updatemarketcrop")
    public ResponseEntity<StandardResponse> updateMarketCrop(@RequestBody MarketCrops marketCrops){
        return marketService.updateMarketCrop(marketCrops);
    }

    @GetMapping("/getallmarketcrops")
    public ResponseEntity<StandardResponse> getAllMarketCrops(){
        return marketService.getAllMarketCrops();
    }

}
