package com.example.timingconsensusscheduler.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninUserDto {
    @NotNull
    @NotBlank
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
