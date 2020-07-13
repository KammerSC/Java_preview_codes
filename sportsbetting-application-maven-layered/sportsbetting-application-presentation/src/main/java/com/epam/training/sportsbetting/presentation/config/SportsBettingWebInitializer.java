package com.epam.training.sportsbetting.presentation.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SportsBettingWebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(WebAppConfig.class);
        webAppContext.setServletContext(servletContext);
        
        ServletRegistration.Dynamic servlet = servletContext.addServlet("sportsbetting", new DispatcherServlet(webAppContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
    

}
