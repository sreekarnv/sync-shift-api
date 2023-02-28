package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.SigninUserInputDto;
import com.example.timingconsensusscheduler.dto.SigninUserResponseDto;
import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import com.example.timingconsensusscheduler.dto.SignupUserResponseDto;
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
    public ResponseEntity<SignupUserResponseDto> signupUser(@RequestBody @Valid SignupUserInputDto request) {
        var token = userService.registerUser(request);
        return new ResponseEntity<SignupUserResponseDto>(token, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninUserResponseDto> signInUser(
            @RequestBody @Valid SigninUserInputDto request
        ) {
        var token = userService.loginUser(request);
        return new ResponseEntity<SigninUserResponseDto>(token, HttpStatus.ACCEPTED);
    }
}