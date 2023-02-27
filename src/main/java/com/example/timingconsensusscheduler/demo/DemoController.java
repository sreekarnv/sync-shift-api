package com.example.timingconsensusscheduler.demo;

import com.example.timingconsensusscheduler.user.Role;
import com.example.timingconsensusscheduler.user.SignupUserInput;
import com.example.timingconsensusscheduler.user.User;
import com.example.timingconsensusscheduler.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(this.userRepository.findAll());
    }

//    Test user signup
    @PostMapping("/users/signup")
    public ResponseEntity<String> signupUser(@RequestBody SignupUserInput request) {
        var user = User
                .builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("User Created Successfully");
    }
}