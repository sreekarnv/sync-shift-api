package com.example.timingconsensusscheduler.repository;

import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilitySlotRespository extends JpaRepository<FacilitySlot, Integer> {
    List<FacilitySlot> findFacilitySlotsByFacility_Id(Integer facility_id);

    List<FacilitySlot> findFacilitySlotsByUser(User user);

    List<FacilitySlot> findFacilitySlotsByUser_Id(Integer id);
}
