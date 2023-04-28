package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.services.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/")
    public ResponseEntity<List<Facility>> getFacilities() {
        var f = facilityService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(f);
    }
}
