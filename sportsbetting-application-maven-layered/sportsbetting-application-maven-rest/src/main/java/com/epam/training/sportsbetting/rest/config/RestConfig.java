package com.epam.training.sportsbetting.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.epam.training.sportsbetting.data.config.DataConfig;
import com.epam.training.sportsbetting.rest.util.OutcomeFinalizer;
import com.epam.training.sportsbetting.rest.util.ReferenceSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.training.sportsbetting")
@Import(value = {DataConfig.class})
// @PropertySource(encoding = "UTF-8", name = "mapping", value = {"classpath:config/mapping.properties"})
public class RestConfig implements WebMvcConfigurer {

    @Bean
    public ReferenceSetter referenceSetter() {
        return new ReferenceSetter();
    }

    @Bean
    public OutcomeFinalizer outcomeFinalizer() {
        return new OutcomeFinalizer();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
