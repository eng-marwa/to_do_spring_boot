package com.example.to_do_spring_boot.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public String appAuthor() {
        return "Marwa Talaat";
    }

    @Bean
    public String appDescription() {
        return "This is a simple To Do Application";
    }

    @Bean
    public String appName() {
        return "To Do Application";
    }

    @Bean
    public String appVersion() {
        return "1.0.0";
    }

}
