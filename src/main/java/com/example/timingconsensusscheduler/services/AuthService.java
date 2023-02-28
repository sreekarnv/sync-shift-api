package com.example.timingconsensusscheduler.services;

import lombok.*;
import com.example.timingconsensusscheduler.dto.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public SignupUserResponseDto registerUser(SignupUserInputDto input) {
        var user = userService.insertUser(input);
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

        var user = userService.getOneByEmail(input.getEmail());
        var token = jwtService.generateToken(user);
        return SigninUserResponseDto.builder().token(token).build();
    }
}
