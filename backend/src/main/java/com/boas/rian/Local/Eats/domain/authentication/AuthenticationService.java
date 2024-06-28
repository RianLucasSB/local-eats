package com.boas.rian.Local.Eats.domain.authentication;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import com.boas.rian.Local.Eats.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String emailWithUserType) throws UsernameNotFoundException {
        String[] split = emailWithUserType.split(":");
        String email = split[0];
        String userType = split[1];

        return repository.findByEmailAndUserType(email, UserType.valueOf(userType));
    }
}
