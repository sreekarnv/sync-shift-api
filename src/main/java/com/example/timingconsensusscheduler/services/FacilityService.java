package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }
}
