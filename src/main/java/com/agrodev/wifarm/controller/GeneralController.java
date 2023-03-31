package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.GeneralServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/general/")
public class GeneralController {

    @Autowired
    private GeneralServices generalServices;

    @GetMapping("/getcountries")
    public ResponseEntity<StandardResponse> getCountries(){
        return generalServices.getCountries();
    }
}
