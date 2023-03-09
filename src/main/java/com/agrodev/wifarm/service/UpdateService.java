package com.agrodev.wifarm.service;

import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jobrunr.scheduling.JobScheduler;


@Service
public class UpdateService {
    @Autowired
    private MarketService marketService;
    @Autowired
    private JobScheduler jobScheduler;

    public void updateMarketService(){
        try {
            jobScheduler.scheduleRecurrently(Cron.hourly(), () -> marketService.updateAccruedAmount());
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
