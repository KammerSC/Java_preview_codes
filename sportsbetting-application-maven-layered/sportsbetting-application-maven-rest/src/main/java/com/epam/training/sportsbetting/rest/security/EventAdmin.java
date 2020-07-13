package com.epam.training.sportsbetting.rest.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.training.sportsbetting.data.entities.AdminEntity;

public class EventAdmin implements UserDetails {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected String password;
    private long id;
    
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    public EventAdmin() {
    }
    
    public EventAdmin(AdminEntity admin) {
        name = admin.getName();
        password = admin.getPassword();
        id = admin.getId();
        authorities.add(new EventAdminAuthority());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Complex logic to determinate is the account expired or not:
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Complex logic to determinate is the account locked or not:
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Complex logic to determinate is the credentials expired or not:
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Complex logic to determinate is the account enabled or not:
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
