package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.entity.Facility;
import com.example.timingconsensusscheduler.entity.FacilitySlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.FacilitySlotRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class FacilitySlotService {
    private final FacilitySlotRespository facilitySlotRespository;

    public List<FacilitySlot> findAllByFacility(Integer facility_id) {
        return facilitySlotRespository.findFacilitySlotsByFacility_Id(facility_id);
    }

    public List<FacilitySlot> findAllByUser(User user) {
        return facilitySlotRespository.findFacilitySlotsByUser(user);
    }

    public List<FacilitySlot> findFacilitySlotsByUser_Id(Integer id) {
        return facilitySlotRespository.findFacilitySlotsByUser_Id(id);
    }

//    public FacilitySlot create(Integer facility_id, Integer user_id, Timestamp start, Timestamp end) {
//        var fs = FacilitySlot
//                .builder()
//                .facility(Facility.builder().id(facility_id).build())
//                .user(User.builder().id(user_id).build())
//                .startTimeStamp(start)
//                .endTimeStamp(end)
//                .build();

//        facilitySlotRespository.i
//    }
}
