package com.agrodev.wifarm.service;

import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UpdateService {

    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private SubcriptionService subcriptionService;
    public void endSubscription(Long accountId, Long days) {
        jobScheduler.schedule(LocalDateTime.now().plusHours(days), () -> subcriptionService.removeSubFromAccount(accountId));
    }

}
