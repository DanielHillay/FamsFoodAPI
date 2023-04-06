package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.entity.StandardResponse;
import com.agrodev.wifarm.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/getdashboard")
    public ResponseEntity<StandardResponse> getDashBoardInfo(@RequestParam("id") Long id){
        return dashboardService.getDashBoardInfo(id);
    }
    @GetMapping("/getdashboardforuser")
    public ResponseEntity<StandardResponse> getDashboardInfoForUser(@RequestParam("id") Long id){
        return dashboardService.getUserDashboardInfo(id);
    }
}
