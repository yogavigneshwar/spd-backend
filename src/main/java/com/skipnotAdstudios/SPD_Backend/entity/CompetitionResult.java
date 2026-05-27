package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "competition_results")
public class CompetitionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "event_name")
    private String eventName;

    private String position;

    private Double score;

    private String timing;

    private String remarks;

    @Column(name = "event_date")
    private LocalDate eventDate;
}