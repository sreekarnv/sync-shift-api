package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.dto.SigninUserResponseDto;
import com.example.timingconsensusscheduler.dto.SignupUserResponseDto;
import lombok.*;

import com.example.timingconsensusscheduler.dto.SigninUserInputDto;
import com.example.timingconsensusscheduler.dto.SignupUserInputDto;
import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.UserRepository;
import com.example.timingconsensusscheduler.utils.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


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

    public User getOneByEmail(SigninUserInputDto data) {
        return userRepository.findByEmail(data.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credentials")
        );
    }

    public SignupUserResponseDto registerUser(SignupUserInputDto input) {
        var user = this.insertUser(input);
        var jwtToken = jwtService.generateToken(user);
        return SignupUserResponseDto.builder().token(jwtToken).build();
    }

    public SigninUserResponseDto loginUser(SigninUserInputDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        var user = userRepository.findByEmail(input.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credentials")
        );
        var token = jwtService.generateToken(user);
        return SigninUserResponseDto.builder().token(token).build();
    }
}