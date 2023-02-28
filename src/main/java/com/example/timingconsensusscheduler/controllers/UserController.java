package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> protectedRoute() {
        return ResponseEntity.status(HttpStatus.OK).body("Message from protected Route");
    }
}