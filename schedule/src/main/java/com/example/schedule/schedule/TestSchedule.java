package com.example.schedule.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@ConditionalOnProperty(name = "do.schedule", havingValue = "true", matchIfMissing = false)
@Component
public class TestSchedule {

    private static final Logger logger = LoggerFactory.getLogger(TestSchedule.class);

    @Scheduled(cron = "0 * * * * *")
    public void testSchedule() throws  Exception{
        logger.info("Begin testSchedule");

        Thread.sleep(5 * 1000);

        logger.info("End testSchedule");
    }



}
