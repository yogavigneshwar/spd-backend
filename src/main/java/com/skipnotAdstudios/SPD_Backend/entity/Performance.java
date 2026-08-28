package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    private String height;

    private String weight;

    private String speed;

    private String stamina;

    private String strength;

    private String flexibility;

    private String remarks;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;
}