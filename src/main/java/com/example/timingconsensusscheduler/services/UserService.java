package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.dto.UserBaseDto;
import lombok.*;

import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.*;
import com.example.timingconsensusscheduler.utils.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User insertUser(SignupUserInputDto data) {
        var role = data.isStaff ? Role.STAFF : Role.STUDENT;
        var user = User
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(role)
                .build();
       return userRepository.save(user);
    }

    public User getOneByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credentials")
        );
    }

    public List<UserBaseDto> getAll() {
        return userRepository.findAllByProjection();
    }
}