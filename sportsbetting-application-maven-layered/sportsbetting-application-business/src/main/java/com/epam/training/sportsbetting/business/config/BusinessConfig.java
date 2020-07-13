package com.epam.training.sportsbetting.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.epam.training.sportsbetting.business.dao.EventDataSource;
import com.epam.training.sportsbetting.business.dao.EventDataSourceImpl;
import com.epam.training.sportsbetting.business.dao.EventRelatedTransformer;
import com.epam.training.sportsbetting.business.dao.Finder;
import com.epam.training.sportsbetting.business.dao.PlayerDao;
import com.epam.training.sportsbetting.business.dao.PlayerDaoImpl;
import com.epam.training.sportsbetting.business.dao.PlayerRelatedTransformer;
import com.epam.training.sportsbetting.data.config.DataConfig;

@Configuration
@Import(DataConfig.class)
public class BusinessConfig {

    @Bean
    public EventDataSource eventDataSource() {
        return new EventDataSourceImpl();
    }

    @Bean
    public PlayerDao playerDao() {
        return new PlayerDaoImpl();
    }

    @Bean
    public EventRelatedTransformer eventRelatedTransformer() {
        return new EventRelatedTransformer();
    }

    @Bean
    public PlayerRelatedTransformer playerRelatedTransformer() {
        return new PlayerRelatedTransformer();
    }
    
    @Bean
    public Finder finder() {
        return new Finder();
    }

}
