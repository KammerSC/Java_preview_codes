package com.epam.training.sportsbetting.business;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.training.sportsbetting.business.config.BusinessConfig;

public class App {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(BusinessConfig.class)) {

        }
    }
}
