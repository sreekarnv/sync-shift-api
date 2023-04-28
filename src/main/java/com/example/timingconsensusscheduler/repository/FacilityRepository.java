package com.example.timingconsensusscheduler.repository;

import com.example.timingconsensusscheduler.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
}
