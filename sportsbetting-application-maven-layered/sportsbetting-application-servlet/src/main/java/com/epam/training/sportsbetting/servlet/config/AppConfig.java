package com.epam.training.sportsbetting.servlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.epam.training.sportsbetting.business.config.BusinessConfig;

@Configuration
@Import(BusinessConfig.class)
public class AppConfig {
    
        @Bean
        public String message() {
            return "Spring is working";
        }
        
}
