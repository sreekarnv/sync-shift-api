package com.example.timingconsensusscheduler.services;

import com.example.timingconsensusscheduler.entity.User;
import com.example.timingconsensusscheduler.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.*;
import com.example.timingconsensusscheduler.dto.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public boolean validateBitsEmail(String email) {
        return email.endsWith(".bits-pilani.ac.in");
    }

    public SignupUserResponseDto registerUser(SignupUserInputDto input) {
        if (!validateBitsEmail(input.getEmail())) {
            throw new ValidationException("Please use your BITS email address.");
        }

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

    public void changePassword(
            UpdatePasswordRequestDto body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (User) auth.getPrincipal();

        if (passwordEncoder.matches(body.getOldPassword(), user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(body.getNewPassword());
            user.setPassword(encodedPassword);
            var sTime = user.getDefaultStartAvailableTime();
            var eTime = user.getDefaultEndAvailableTime();
            user.setDefaultEndAvailableTime(null);
            user.setDefaultStartAvailableTime(null);
            userRepository.save(user);
            userService.findOneAndUpdateDefaultSlots(user, sTime, eTime);
        } else {
            throw new ValidationException("Your Current password is wrong");
        }
    }
}
