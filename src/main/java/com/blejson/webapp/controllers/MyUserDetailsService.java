package com.blejson.webapp.controllers;

import com.blejson.webapp.domain.User;
import com.blejson.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Blejson on 14.07.2020
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+userName));
        return user.map(MyUserDetails::new).get();
    }
    public boolean isUserPresent(String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isPresent();
    }
}
