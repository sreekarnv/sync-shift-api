package com.example.timingconsensusscheduler.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserBaseDto {

    private String name;
    private String email;
    private Integer id;
    private String role;

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
}
