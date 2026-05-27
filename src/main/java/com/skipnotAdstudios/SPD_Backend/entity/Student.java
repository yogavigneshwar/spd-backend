package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    private Integer age;

    @Column(name = "parent_mobile")
    private String parentMobile;

    private String password;

    @Column(name = "student_code", unique = true)
    private String studentCode;

    @Column(name = "training_category")
    private String trainingCategory;

    @Column(name = "current_day")
    private Integer currentDay;

   @Column(name = "coach_id")
   private Integer coachId;

    @Column(name = "academy_id")
    private Integer academyId;
}