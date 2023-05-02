package com.example.timingconsensusscheduler.repository;

import com.example.timingconsensusscheduler.dto.UserBaseDto;
import com.example.timingconsensusscheduler.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

//    @Query(nativeQuery = true)
//    List<UserBaseDto> findAllByProjection();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.defaultStartAvailableTime = ?1, u.defaultEndAvailableTime = ?2 WHERE u.id = ?3")
    void updateDefaultSlots(
            @Param("startTime") Time startTime,
            @Param("endTime") Time endTime,
            @Param("id") Integer id
    );
}