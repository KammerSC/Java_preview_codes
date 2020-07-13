package com.epam.training.sportsbetting.presentation.security;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.training.sportsbetting.business.dao.PlayerDao;
import com.epam.training.sportsbetting.domain.Player;

@Service("userDetailsService")
public class SportsBettingUserDetailService implements UserDetailsService  {

    
    @Autowired
    PlayerDao dao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Player player = dao.getPlayerByEmail(username);
            return new WebUser(player);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Invalid username.");
        }
    }
    
    

}
