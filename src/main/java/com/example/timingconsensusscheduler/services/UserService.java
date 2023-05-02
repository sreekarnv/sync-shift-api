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

import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;

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

    public User findOneById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void findOneAndUpdateDefaultSlots(User user, Time startTime, Time endTime) {
        userRepository.updateDefaultSlots(startTime, endTime, user.getId());
    }
}