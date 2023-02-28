package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @GetMapping("")
    public ResponseEntity<String> signupUser() {
        return new ResponseEntity<String>("Post Controller Protected", HttpStatus.CREATED);
    }
}
