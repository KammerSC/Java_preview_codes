package com.epam.training.sportsbetting.presentation.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.epam.training.sportsbetting.business.config.BusinessConfig;
import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterface;
import com.epam.training.sportsbetting.presentation.web.datasource.EventAccessInterfaceImp;
import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterface;
import com.epam.training.sportsbetting.presentation.web.datasource.PlayerAccessInterfaceImpl;
import com.epam.training.sportsbetting.presentation.web.page.bet.converter.BetToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.page.event.converter.EventToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.page.home.converter.PlayerToDetailsConverter;
import com.epam.training.sportsbetting.presentation.web.page.home.converter.WagerToWagerDesctransformer;
import com.epam.training.sportsbetting.presentation.web.page.wager.converter.OutcomeToDescriptionConverter;
import com.epam.training.sportsbetting.presentation.web.text.PageTextProvider;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.training.sportsbetting")
@Import(value = {BusinessConfig.class})
@PropertySource(encoding = "UTF-8", name = "mapping", value = {"classpath:config/mapping.properties"})
public class WebAppConfig implements WebMvcConfigurer {
    @Bean
    public PlayerAccessInterface playerAccessInterface() {
        return new PlayerAccessInterfaceImpl();
    }
    @Bean
    public EventAccessInterface eventAccessInterface() {
        return new EventAccessInterfaceImp();
    }

    @Bean
    public WagerToWagerDesctransformer wagerToWagerDescTransformer() {
        return new WagerToWagerDesctransformer();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PlayerToDetailsConverter());
        registry.addConverter(new BetToDescriptionConverter());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
        ret.setBasename("classpath:locale/lang");
        ret.setCacheSeconds(1);
        ret.setUseCodeAsDefaultMessage(true);
        ret.setDefaultEncoding("utf-8");
        return ret;
    }

    @Bean(name = "localeResolver")
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("locale");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }
    
    @Bean
    public PageTextProvider pageTextProvider() {
        return new PageTextProvider();
    }
    
    @Bean
    public EventToDescriptionConverter eventToDescriptionConverter() {
        return new EventToDescriptionConverter();
    }
    
    @Bean
    public BetToDescriptionConverter betToDescriptionConverter() {
        return new BetToDescriptionConverter();
    }
    
    @Bean
    public OutcomeToDescriptionConverter  outcomeToDescriptionConverter() {
        return new OutcomeToDescriptionConverter();
    }

}
