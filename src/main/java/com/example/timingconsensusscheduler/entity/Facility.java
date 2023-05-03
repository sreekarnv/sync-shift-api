package com.example.timingconsensusscheduler.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    private String location;

    @NotNull
    @NotBlank
    private String type;

    private Boolean isAvailable;

    @NotBlank
    private LocalTime availableStartTime;

    @NotBlank
    private LocalTime availableEndTime;
}
