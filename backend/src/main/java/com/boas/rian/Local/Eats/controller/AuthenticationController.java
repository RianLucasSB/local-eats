package com.boas.rian.Local.Eats.controller;

import com.boas.rian.Local.Eats.domain.enums.UserType;
import com.boas.rian.Local.Eats.domain.partner.CreatePartnerDto;
import com.boas.rian.Local.Eats.domain.partner.Partner;
import com.boas.rian.Local.Eats.domain.partner.PartnerRepository;
import com.boas.rian.Local.Eats.domain.user.*;
import com.boas.rian.Local.Eats.infra.security.LoginResponseDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartnerRepository partnerRepository;

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


    @PostMapping("/partner/register")
    public ResponseEntity<CreateUserResponseDto> registerPartner(@RequestBody CreatePartnerDto body, UriComponentsBuilder uriBuilder){
        User user = new User(body);

        user.setPassword(passwordEncoder.encode(body.password()));

        userRepository.save(user);

        Partner partner = new Partner(user, body.cnpj());

        partnerRepository.save(partner);

        String tokenJWT = tokenService.generateToken(user);

        CreateUserResponseDto userResponse = new CreateUserResponseDto(user, tokenJWT);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto body){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(body.email() + ":" + UserType.CUSTOMER, body.password());

        var authentication = manager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDto(token));

    }


    @PostMapping("/partner/login")
    public ResponseEntity<LoginResponseDto> loginPartner(@RequestBody LoginDto body){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(body.email() + ":" + UserType.PARTNER, body.password());

        var authentication = manager.authenticate(usernamePasswordAuthenticationToken);

        String token = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDto(token));

    }
}
