package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.MarketCrops;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @PutMapping("/updatemarketcrop")
    public ResponseEntity<StandardResponse> updateMarketCrop(@RequestBody MarketCrops marketCrops){
        return marketService.updateMarketCrop(marketCrops);
    }

    @PostMapping("/createcrop")
    public ResponseEntity<StandardResponse> createMarketCrop(@RequestBody MarketCrops marketCrops){
        return marketService.createMarketCrop(marketCrops);
    }
    @GetMapping("/getallmarketcrops")
    public ResponseEntity<StandardResponse> getAllMarketCrops(){
        return marketService.getAllMarketCrops();
    }

    @PostMapping("/uploadphoto")
    public ResponseEntity<StandardResponse> uploadPhoto(@RequestParam("image") MultipartFile multipartFile, @RequestParam("id") Long id) throws IOException {
        return marketService.uploadPhoto(multipartFile, id);
    }

}
