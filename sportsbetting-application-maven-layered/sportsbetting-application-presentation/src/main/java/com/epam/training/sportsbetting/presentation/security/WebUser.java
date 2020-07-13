package com.epam.training.sportsbetting.presentation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.training.sportsbetting.domain.Player;

public class WebUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    protected String userName;
    protected String password;
    private long userId;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    public WebUser() {
    }
    
    public WebUser(Player player) {
        userName = player.getEmail();
        password = player.getPassword();
        userId = player.getId();
        authorities.add(new BettingUserAuthority());
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
        return userName;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
