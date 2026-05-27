package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "coach_name", nullable = false)
    private String coachName;

    @Column(unique = true)
    private String mobile;

    private String password;

    private String specialization;
}