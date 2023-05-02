package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.dto.BookFacilitySlotRequestDto;
import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.services.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/facilities")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilitySlotService facilitySlotService;

    @GetMapping("/")
    public ResponseEntity<List<Facility>> getFacilities() {
        var f = facilityService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(f);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Facility>> getFacility(@PathVariable Integer id) {
        var f = facilityService.getOneById(id);

        if (f.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(f);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(f);
    }

    @GetMapping("/slots/{id}")
    public ResponseEntity<List<FacilitySlot>> getSlotsOfFacility(@PathVariable Integer id) {
        var fs = facilitySlotService.findAllByFacility(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fs);
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/slots/{id}")
    public ResponseEntity<Boolean> bookFacilitySlot(
            @PathVariable Integer id, @RequestBody @Valid BookFacilitySlotRequestDto body)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();
        facilitySlotService.bookSlot(
                id,
                user,
                Timestamp.valueOf(body.getStartTimeStamp()), Timestamp.valueOf(body.getEndTimeStamp()));
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

//    @PostMapping("/slots/{id}")
//    public ResponseEntity<List<FacilitySlot>> createSlotForFacility(@PathVariable Integer id) {
//        var fs = facilitySlotService.
//    }
}
