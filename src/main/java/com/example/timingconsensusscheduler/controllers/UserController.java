package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.UserBaseDto;
import com.example.timingconsensusscheduler.services.UserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserBaseDto>> getMembers() {
       var users = userService.getAll();
       return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/health")
    public ResponseEntity<String> getString() {
        return ResponseEntity.status(HttpStatus.OK).body("Yo!!");
    }
}