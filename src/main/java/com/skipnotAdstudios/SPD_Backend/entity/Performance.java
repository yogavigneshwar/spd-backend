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

    private Double height;

    private Double weight;

    private Double speed;

    private Double stamina;

    private Double strength;

    private Double flexibility;

    private String remarks;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;
}