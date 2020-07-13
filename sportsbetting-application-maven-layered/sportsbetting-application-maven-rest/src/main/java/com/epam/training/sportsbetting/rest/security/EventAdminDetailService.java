package com.epam.training.sportsbetting.rest.security;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.epam.training.sportsbetting.data.crud.AdminRepository;
import com.epam.training.sportsbetting.data.entities.AdminEntity;

@Service("eventAdminDetailsService")
public class EventAdminDetailService implements UserDetailsService  {
    
    private AdminRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AdminEntity admin = repo.findByName(username);
            return new EventAdmin(admin);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Invalid username.");
        }
    }

    @Autowired
    public void setRepo(AdminRepository repo) {
        this.repo = repo;
    }
}
