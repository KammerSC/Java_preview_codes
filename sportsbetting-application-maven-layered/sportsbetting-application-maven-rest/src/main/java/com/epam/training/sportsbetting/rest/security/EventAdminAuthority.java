package com.epam.training.sportsbetting.rest.security;

import org.springframework.security.core.GrantedAuthority;

public class EventAdminAuthority implements GrantedAuthority{
    private static final long serialVersionUID = 1L;
    private static final String ROLE = "ADMIN"; 
    
    @Override
    public String getAuthority() {
        return ROLE;
    }
}
