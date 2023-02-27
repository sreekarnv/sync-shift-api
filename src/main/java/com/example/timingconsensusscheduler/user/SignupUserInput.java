package com.example.timingconsensusscheduler.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupUserInput {
    private String email;
    private String name;
    private String password;
}
