package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.DefaultAvailableRequestDto;
import com.example.timingconsensusscheduler.dto.DefaultAvailableResponseDto;
import com.example.timingconsensusscheduler.dto.UserBaseDto;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.services.FacilitySlotService;
import com.example.timingconsensusscheduler.services.JwtService;
import com.example.timingconsensusscheduler.services.UserService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final FacilitySlotService facilitySlotService;
    private final JwtService jwtService;

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

//    Logged in user facility slots
    @GetMapping("/slots")
    public ResponseEntity<List<FacilitySlot>> getLoggedInUserFacilitySlots() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        var slots = facilitySlotService.findAllByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(slots);
    }

//    User facility slots
    @GetMapping("/slots/{id}")
    public ResponseEntity<List<FacilitySlot>> getUserFacilitySlots(@PathVariable Integer id) {
        var slots = facilitySlotService.findFacilitySlotsByUser_Id(id);
        return ResponseEntity.status(HttpStatus.OK).body(slots);
    }

//    User details
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetail(@PathVariable Integer id) {
        var user = userService.findOneById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/set-default-available")
    public ResponseEntity<DefaultAvailableResponseDto> setDefaultAvailable(
            @RequestBody @Valid DefaultAvailableRequestDto body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        userService.findOneAndUpdateDefaultSlots(
                user,
                Time.valueOf(body.getStartTime() + ":00"),
                Time.valueOf(body.getEndTime() + ":00")
        );
        var token = jwtService.generateToken(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                DefaultAvailableResponseDto
                        .builder()
                        .token(token)
                .build()
        );
    }
}