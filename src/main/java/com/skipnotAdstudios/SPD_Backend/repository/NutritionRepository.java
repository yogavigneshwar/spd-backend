package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NutritionRepository extends JpaRepository<Nutrition, Integer> {
    List<Nutrition> findByStudentIdOrderByLogDateDesc(Integer studentId);
    Optional<Nutrition> findByStudentIdAndLogDate(Integer studentId, LocalDate logDate);
    List<Nutrition> findByLogDate(LocalDate logDate);
    List<Nutrition> findAllByOrderByLogDateDesc();
}
