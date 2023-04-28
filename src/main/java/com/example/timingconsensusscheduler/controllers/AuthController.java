package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.SigninUserInputDto;
import com.example.timingconsensusscheduler.dto.SigninUserResponseDto;
import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import com.example.timingconsensusscheduler.dto.SignupUserResponseDto;
import com.example.timingconsensusscheduler.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupUserResponseDto> signupUser(@RequestBody @Valid SignupUserInputDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.registerUser(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninUserResponseDto> signInUser(
            @RequestBody @Valid SigninUserInputDto request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.loginUser(request));
    }
}
