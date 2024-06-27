package com.boas.rian.Local.Eats.controller;

import com.boas.rian.Local.Eats.domain.user.User;
import com.boas.rian.Local.Eats.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
