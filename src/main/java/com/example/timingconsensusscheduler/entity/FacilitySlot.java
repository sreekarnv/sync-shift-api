package com.example.timingconsensusscheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="facility_slot")
public class FacilitySlot {
        @Id
        @GeneratedValue
        private Integer id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "facility_id")
        private Facility facility;

        private Timestamp startTimeStamp;
        private Timestamp endTimeStamp;
}
