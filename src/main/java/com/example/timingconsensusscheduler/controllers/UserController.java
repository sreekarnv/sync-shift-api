package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.SigninUserDto;
import com.example.timingconsensusscheduler.dto.SignupUserDto;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<User> signupUser(@RequestBody @Valid SignupUserDto request) {
        var user = userService.insertUser(request);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signInUser(
            @RequestBody @Valid SigninUserDto request
        ) {
        var user = userService.getOneByEmail(request);
        return ResponseEntity.ok(user);
    }
}