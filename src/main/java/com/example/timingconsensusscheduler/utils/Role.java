package com.example.timingconsensusscheduler.utils;

import org.springframework.security.core.GrantedAuthority;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    public static final String STUDENT = "STUDENT";
    public static final String STAFF = "STAFF";

    private String authority;
}