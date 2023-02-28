package com.example.timingconsensusscheduler.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupUserInputDto {
    @NotBlank(message = "Please provide your full name")
    @NotNull(message = "Please provide your full name")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;

    @NotNull(message =  "Please provide you email address")
    @NotBlank(message = "Your email address cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message =  "Please provide your password")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must atleast contain 6 characters")
    private String password;
}
