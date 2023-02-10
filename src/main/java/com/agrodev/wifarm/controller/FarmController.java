package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Farm;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farm")
public class FarmController {
    @Autowired
    private FarmService farmService;

    @PostMapping("/createfarm")
    public ResponseEntity<StandardResponse> createFarm(@RequestBody Farm farm){
        return farmService.createFarm(farm);
    }

    @PutMapping("/updatefarm")
    public ResponseEntity<StandardResponse> updateFarm(@RequestBody Farm farm){
        return farmService.updateFarm(farm);
    }

    @GetMapping("/getfarmbycustomerid")
    public ResponseEntity<StandardResponse> getFarmByCustomer(@RequestParam("customerId") String customerId){
        return farmService.getFarmByCustomerId(customerId);
    }

    @PostMapping("/createfarmforcustomer")
    public ResponseEntity<StandardResponse> createFarmForUser(@RequestBody Farm farm, @RequestParam("userId") Long userId){
        return farmService.createFarmForCustomer(farm, userId);
    }

    @PostMapping("/updatefarmforcustomer")
    public ResponseEntity<StandardResponse> updateFarmForUser(@RequestBody Farm farm, @RequestParam("userId") Long userId){
        return farmService.updateFarmForUser(farm, userId);
    }

}
