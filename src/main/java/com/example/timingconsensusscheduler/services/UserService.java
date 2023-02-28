package com.example.timingconsensusscheduler.services;

import lombok.*;

import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.UserRepository;
import com.example.timingconsensusscheduler.utils.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User insertUser(SignupUserInputDto data) {
        var user = User
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.STUDENT)
                .build();
       return userRepository.save(user);
    }

    public User getOneByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credentials")
        );
    }
}