package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerCronConfiguration {

    @Autowired
    private GlobalConfig globalConfig;

    @Bean
    public String schedulerCronExample(){
        return globalConfig.getSchedulerPath();
    }
}
