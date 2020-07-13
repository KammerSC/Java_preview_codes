package com.epam.training.sportsbetting.rest.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SportsBettingRestInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(RestConfig.class);
        webAppContext.setServletContext(servletContext);
        
        ServletRegistration.Dynamic servlet = servletContext.addServlet("sportsbetting", new DispatcherServlet(webAppContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
    

}
