package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "training_programs")
public class TrainingProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    @Column(name = "day_number")
    private Integer dayNumber;

    @Column(name = "training_title")
    private String trainingTitle;

    private String description;

    @Column(name = "nutrition_note")
    private String nutritionNote;
}