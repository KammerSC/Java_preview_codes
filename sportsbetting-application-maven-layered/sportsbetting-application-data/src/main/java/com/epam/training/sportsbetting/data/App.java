package com.epam.training.sportsbetting.data;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.training.sportsbetting.data.config.DataConfig;
import com.epam.training.sportsbetting.data.crud.AdminRepository;
import com.epam.training.sportsbetting.data.entities.AdminEntity;

public class App {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(DataConfig.class)) {
            
        }
    }
}
