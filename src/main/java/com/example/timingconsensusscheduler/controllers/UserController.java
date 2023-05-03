package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.CreateBookMemberSlotRequestDto;
import com.example.timingconsensusscheduler.dto.DefaultAvailableRequestDto;
import com.example.timingconsensusscheduler.dto.DefaultAvailableResponseDto;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.MemberSlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.services.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final FacilitySlotService facilitySlotService;
    private final JwtService jwtService;
    private final MemberSlotService memberSlotService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getMembers() {
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

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/members/slots/{id}")
    public ResponseEntity<Boolean> createBookMemberSlot(
            @RequestBody @Valid CreateBookMemberSlotRequestDto body,
            @PathVariable Integer id
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();

        memberSlotService.bookSlot(id, user, Timestamp.valueOf(body.getStartTimeStamp()), Timestamp.valueOf(body.getEndTimeStamp()));

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/members/slots/{id}")
    public ResponseEntity<List<MemberSlot>> getMemberSlots(
            @PathVariable Integer id
    ) {
        var body = memberSlotService.findAll(id);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<String> withdrawUser(@PathVariable Integer userId) {
        userService.withdrawUser(userId);
        return ResponseEntity.ok("User withdrawn successfully");
    }
}