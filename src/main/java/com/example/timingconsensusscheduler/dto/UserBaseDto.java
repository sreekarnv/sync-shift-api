package com.example.timingconsensusscheduler.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserBaseDto {

    private String name;
    private String email;
    private Integer id;
    private String role;

    @Null
    private LocalTime defaultStartAvailableTime;

    @Null
    private LocalTime defaultEndAvailableTime;

    String getName() {
        return name;
    }
    String getEmail() {
        return email;
    }
    Integer getId() {
        return id;
    }
    String getRole() {
        return role;
    }

    LocalTime getDefaultStartAvailableTime() {
        return defaultStartAvailableTime;
    }
    LocalTime getDefaultEndAvailableTime() {return defaultEndAvailableTime;}
}
