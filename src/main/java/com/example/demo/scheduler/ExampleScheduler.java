package com.example.demo.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ExampleScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "#{@schedulerCronExample}")
    public void scheduler(){
        logger.info("scheduler 동작하고 있음 : {}", Calendar.getInstance().getTime());
    }
}
