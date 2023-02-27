package com.example.timingconsensusscheduler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User insertUser(SignupUserInput data) {
        var user = User
                .builder()
                .email(data.getEmail())
                .name(data.getName())
                .password(passwordEncoder.encode(data.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return user;
    }

    public User getOneByEmail(SignInUserInput data) {
        return userRepository.findByEmail(data.getEmail()).orElseThrow();
    }
}
