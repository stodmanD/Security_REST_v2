package com.stolypin.securityrest.services;

import com.stolypin.securityrest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.stolypin.securityrest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;



@Service
public class userDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public userDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
