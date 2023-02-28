package com.example.timingconsensusscheduler.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninUserInputDto {
    @NotNull(message =  "Please provide you email address")
    @NotBlank(message = "Your email address cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message =  "Please provide your password")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}

