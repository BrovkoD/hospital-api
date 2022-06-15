package com.brovko.hospitalcoursework.service;

import com.brovko.hospitalcoursework.configuration.security.MyUserDetails;
import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserPOJO> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No user found");
        }

        return new MyUserDetails(user.get());
    }
}
