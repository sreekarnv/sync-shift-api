package com.example.timingconsensusscheduler.repository;

import com.example.timingconsensusscheduler.entity.MemberSlot;
import com.example.timingconsensusscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberSlotRepository extends JpaRepository<MemberSlot, Integer> {
    List<MemberSlot> findAllByAccepted(User user);

    List<MemberSlot> findAllByRequested(User user);
}

