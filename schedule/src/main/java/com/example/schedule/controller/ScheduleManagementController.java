package com.example.schedule.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;


@RestController
public class ScheduleManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleManagementController.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private HashMap<String, ScheduledFuture<?>> mapSchedule = new HashMap<>();

    @GetMapping("/add")
    public void addSchedule(@RequestParam String companyId) {

        CronTrigger cronTrigger = new CronTrigger("0 * * * * *");
        Runnable runnable = createRunable(companyId);

        logger.info("thread pool  : {} ", threadPoolTaskScheduler.getPoolSize());

        logger.info("Begin add schedule : {}", companyId);

        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(runnable, cronTrigger);

        mapSchedule.put(companyId, scheduledFuture);

        logger.info("End add schedule : {}", companyId);
    }

    @GetMapping("/list")
    public List<String> listSchedule() {
        return mapSchedule.keySet().stream().toList();
    }


    @GetMapping("/remove")
    public void remove(@RequestParam String companyId) {
        mapSchedule.get(companyId).cancel(false);
        mapSchedule.remove(companyId);
    }


    public Runnable createRunable(String companyId) {
        return new Runnable() {
            @Override
            public void run() {
                logger.info("Begin schedule for companyId : {}", companyId);
                try {
                    Thread.sleep(5 * 1000);
                } catch (Exception ex ) {

                }
                logger.info("End schedule for companyId : {}", companyId);
            }
        };
    }



}
