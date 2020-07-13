package com.epam.training.sportsbetting.presentation.security;

import org.springframework.security.core.GrantedAuthority;

public class BettingUserAuthority implements GrantedAuthority{
    private static final long serialVersionUID = 1L;
    private static final String ROLE = "USER"; 
    
    @Override
    public String getAuthority() {
        return ROLE;
    }
}
