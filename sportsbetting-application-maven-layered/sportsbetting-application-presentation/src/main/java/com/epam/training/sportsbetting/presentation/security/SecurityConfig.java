package com.epam.training.sportsbetting.presentation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private SportsBettingUserDetailService detailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login", "/registration").permitAll()
                .and()
                .authorizeRequests().anyRequest().hasAnyAuthority("USER")
                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login").defaultSuccessUrl("/home").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }
    
    @Autowired
    public void setDetailService(SportsBettingUserDetailService detailService) {
        this.detailService = detailService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
