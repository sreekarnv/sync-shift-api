package com.example.timingconsensusscheduler.dto;


import lombok.*;

@Builder
@AllArgsConstructor
public class JwtPayloadDto {
    public Integer id;
    public String name;
    public String email;
    public String role;
}

