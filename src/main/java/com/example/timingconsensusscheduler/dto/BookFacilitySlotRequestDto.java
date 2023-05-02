package com.example.timingconsensusscheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookFacilitySlotRequestDto {
    @NotNull(message =  "Please provide startTime")
    @NotBlank(message = "startTime cannot be empty")
    private String startTimeStamp;

    @NotNull(message =  "Please provide endTime")
    @NotBlank(message = "endTime cannot be empty")
    private String endTimeStamp;
}
