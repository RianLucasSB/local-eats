package com.boas.rian.Local.Eats.controller;

import com.boas.rian.Local.Eats.domain.user.CreateUserDto;
import com.boas.rian.Local.Eats.domain.user.CreateUserResponseDto;
import com.boas.rian.Local.Eats.domain.user.User;
import com.boas.rian.Local.Eats.domain.user.UserRepository;
import com.boas.rian.Local.Eats.infra.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDto> registerUser(@RequestBody CreateUserDto body, UriComponentsBuilder uriBuilder){
        User user = new User(body);

        user.setPassword(passwordEncoder.encode(body.password()));

        userRepository.save(user);

        String tokenJWT = tokenService.generateToken(user);

        CreateUserResponseDto userResponse = new CreateUserResponseDto(user, tokenJWT);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }
}
