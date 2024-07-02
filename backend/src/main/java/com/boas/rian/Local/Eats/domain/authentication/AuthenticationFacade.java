package com.boas.rian.Local.Eats.domain.authentication;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import com.boas.rian.Local.Eats.domain.user.User;
import com.boas.rian.Local.Eats.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Autowired
    UserRepository userRepository;

    @Override
    public User retrieveUser(UserType userType) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmailAndUserType(userDetails.getUsername(), userType);

        if (user != null){
            return user;
        }

        throw new UsernameNotFoundException("Username " + userDetails.getUsername() + "not found for type");
    }
}
