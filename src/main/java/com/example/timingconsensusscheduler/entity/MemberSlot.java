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
@Table(name="member_slot")
public class MemberSlot {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_id")
    private User requested;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accepted_id")
    private User accepted;

    private Timestamp startTimeStamp;
    private Timestamp endTimeStamp;
}
