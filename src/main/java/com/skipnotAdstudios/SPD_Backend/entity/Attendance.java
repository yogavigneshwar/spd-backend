package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "coach_id", nullable = false)
    private Integer coachId;
    
    @Column(name = "attendance_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String status;

    private String remarks;

    @Column(name = "scanned_at")
    private java.time.LocalDateTime scannedAt;
}