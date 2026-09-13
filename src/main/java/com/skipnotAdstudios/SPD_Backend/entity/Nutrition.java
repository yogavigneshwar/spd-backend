package com.skipnotAdstudios.SPD_Backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nutrition_logs")
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "log_date", nullable = false)
    private LocalDate logDate;

    @Column(name = "wake_up_time")
    private String wakeUpTime;

    @Column(name = "before_training", columnDefinition = "TEXT")
    private String beforeTraining;

    @Column(name = "breakfast", columnDefinition = "TEXT")
    private String breakfast;

    @Column(name = "snacks", columnDefinition = "TEXT")
    private String snacks;

    @Column(name = "lunch", columnDefinition = "TEXT")
    private String lunch;

    @Column(name = "pre_training", columnDefinition = "TEXT")
    private String preTraining;

    @Column(name = "after_training", columnDefinition = "TEXT")
    private String afterTraining;

    @Column(name = "dinner", columnDefinition = "TEXT")
    private String dinner;

    @Column(name = "sleep_time")
    private String sleepTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
