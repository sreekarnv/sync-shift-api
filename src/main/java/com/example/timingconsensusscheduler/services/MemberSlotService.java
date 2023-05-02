package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.entity.MemberSlot;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.MemberSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberSlotService {
    private final UserService userService;
    private final MemberSlotRepository memberSlotRepository;

    public void bookSlot(Integer member_id, User user, Timestamp start, Timestamp end) {
        var f = User.builder().id(member_id).build();
        var ms = MemberSlot.builder().accepted(f).requested(user).startTimeStamp(start).endTimeStamp(end).build();
        memberSlotRepository.save(ms);
    }

    public List<MemberSlot> findAll(Integer id) {
        var user = userService.findOneById(id);
        var requested = memberSlotRepository.findAllByRequested(user);
        var accepted = memberSlotRepository.findAllByAccepted(user);
        requested.addAll(accepted);
        return requested;
    }
}
