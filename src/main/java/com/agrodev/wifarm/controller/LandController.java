package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.Land;
import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.LandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/land")
public class LandController {
    @Autowired
    private LandService landService;

    @PostMapping("/reigsterland")
    public ResponseEntity<StandardResponse> registerLand(@RequestBody Land land){
        return landService.registerNewLand(land);
    }
}
