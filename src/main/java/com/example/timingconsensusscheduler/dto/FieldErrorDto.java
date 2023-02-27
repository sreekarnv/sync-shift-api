package com.example.timingconsensusscheduler.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class FieldErrorDto {
    private String field;
    private String message;
}
