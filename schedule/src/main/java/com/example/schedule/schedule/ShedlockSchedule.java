package com.example.schedule.schedule;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShedlockSchedule {

    private static final Logger logger = LoggerFactory.getLogger(ShedlockSchedule.class);

    @SchedulerLock(name = "testSchedule", lockAtLeastFor = "PT2M", lockAtMostFor = "PT10M")
    @Scheduled(cron = "0 * * * * *")
    public void testSchedule() throws  Exception{
        logger.info("Begin testSchedule in lock");

        Thread.sleep(30 * 1000);

        logger.info("End testSchedule in lock");
    }


}
