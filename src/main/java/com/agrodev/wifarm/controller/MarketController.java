package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @PutMapping("/updatemarketcrop")
    public ResponseEntity<StandardResponse> updateMarketCrop(@RequestBody MarketCrops marketCrops){
        return marketService.updateMarketCrop(marketCrops);
    }
}
