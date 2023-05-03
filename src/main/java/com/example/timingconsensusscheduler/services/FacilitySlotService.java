package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.FacilitySlotRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class FacilitySlotService {
    private final FacilitySlotRespository facilitySlotRespository;
    private final FacilityService facilityService;

    public List<FacilitySlot> findAllByFacility(Integer facility_id) {
        return facilitySlotRespository.findFacilitySlotsByFacility_Id(facility_id);
    }

    public List<FacilitySlot> findAllByUser(User user) {
        return facilitySlotRespository.findFacilitySlotsByUser(user);
    }

    public List<FacilitySlot> findFacilitySlotsByUser_Id(Integer id) {
        return facilitySlotRespository.findFacilitySlotsByUser_Id(id);
    }

    public void bookSlot(Integer facility_id, User user, Timestamp start, Timestamp end) {
        Facility f = facilityService.getOneById(facility_id).orElseThrow();
        var fs = FacilitySlot.builder()
                .facility(f)
                .user(user)
                .startTimeStamp(start)
                .endTimeStamp(end)
                .build();
        facilitySlotRespository.save(fs);
    }

}
