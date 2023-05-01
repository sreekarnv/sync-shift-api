package com.example.timingconsensusscheduler.controllers;

import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.services.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

//    @PostMapping("/slots/{id}")
//    public ResponseEntity<List<FacilitySlot>> createSlotForFacility(@PathVariable Integer id) {
//        var fs = facilitySlotService.
//    }
}
