package com.example.timingconsensusscheduler.dto;


import lombok.*;

import java.sql.Time;

@Builder
@AllArgsConstructor
public class JwtPayloadDto {
    public Integer id;
    public String name;
    public String email;
    public String role;
    public Time defaultStartAvailableTime;
    public Time defaultEndAvailableTime;
}

