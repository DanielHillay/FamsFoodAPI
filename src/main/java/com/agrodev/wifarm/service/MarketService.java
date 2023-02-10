package com.agrodev.wifarm.service;

import com.agrodev.wifarm.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
    @Autowired
    private CropRepository cropRepository;
}
