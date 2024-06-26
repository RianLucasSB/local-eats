package com.boas.rian.Local.Eats.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity register(){
        return ResponseEntity.ok().build();
    }
}
