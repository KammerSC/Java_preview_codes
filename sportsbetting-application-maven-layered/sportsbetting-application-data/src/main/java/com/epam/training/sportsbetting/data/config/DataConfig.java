package com.epam.training.sportsbetting.data.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.epam.training.sportsbetting.data.facade.EventRelatedFacade;
import com.epam.training.sportsbetting.data.facade.EventRelatedFacadeImplementation;
import com.epam.training.sportsbetting.data.facade.PlayerRelatedFacade;
import com.epam.training.sportsbetting.data.facade.PlayerRelatedFacadeImpl;

/** Configuration class of Persistent layer.
 * */
@Configuration
@EnableJpaRepositories(basePackages = {"com.epam.training.sportsbetting.data.crud"})
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/sportsbetting_david_borbely?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan(new String[]{"com.epam.training.sportsbetting.data.entities"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        emfb.setJpaProperties(additionalProperties());
        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        return transactionManager;
    }

    private Properties additionalProperties() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        prop.setProperty("hibernate.show_sql", "false");
        prop.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return prop;
    }

    @Bean
    public PlayerRelatedFacade playerRelatedFacade() {
        return new PlayerRelatedFacadeImpl();
    }

    @Bean
    public EventRelatedFacade eventRelatedFacade() {
        return new EventRelatedFacadeImplementation();
    }

}
