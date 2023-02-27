package com.example.timingconsensusscheduler.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> signupUser(@RequestBody SignupUserInput request) {
        var user = userService.insertUser(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signInUser(@RequestBody SignInUserInput request) {
        var user = userService.getOneByEmail(request);
        return ResponseEntity.ok(user);
    }
}
