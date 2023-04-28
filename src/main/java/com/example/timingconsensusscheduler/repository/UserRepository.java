package com.example.timingconsensusscheduler.repository;

import com.example.timingconsensusscheduler.dto.UserBaseDto;
import com.example.timingconsensusscheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true)
    List<UserBaseDto> findAllByProjection();
}