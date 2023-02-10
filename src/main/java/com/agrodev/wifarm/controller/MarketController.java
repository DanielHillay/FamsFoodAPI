package com.agrodev.wifarm.controller;

import com.agrodev.wifarm.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;
}
