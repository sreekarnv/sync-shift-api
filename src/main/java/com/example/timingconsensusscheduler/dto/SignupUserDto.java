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
public class SignupUserDto {
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;

    @NotNull
    @NotBlank
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, message = "Password must atleast contain 6 characters")
    private String password;
}
