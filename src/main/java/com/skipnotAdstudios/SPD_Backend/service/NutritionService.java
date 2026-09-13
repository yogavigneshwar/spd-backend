package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Nutrition;
import com.skipnotAdstudios.SPD_Backend.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class NutritionService {

    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    @Autowired
    private NutritionRepository nutritionRepository;

    public Nutrition saveOrUpdateNutrition(Nutrition nutrition) {
        if (nutrition.getLogDate() == null) {
            nutrition.setLogDate(LocalDate.now(IST_ZONE));
        }
        if (nutrition.getCreatedAt() == null) {
            nutrition.setCreatedAt(LocalDateTime.now(IST_ZONE));
        }

        // Check if entry already exists for student and date
        Optional<Nutrition> existing = nutritionRepository.findByStudentIdAndLogDate(
            nutrition.getStudentId(), nutrition.getLogDate()
        );

        if (existing.isPresent()) {
            Nutrition existingRecord = existing.get();
            existingRecord.setWakeUpTime(nutrition.getWakeUpTime());
            existingRecord.setBeforeTraining(nutrition.getBeforeTraining());
            existingRecord.setBreakfast(nutrition.getBreakfast());
            existingRecord.setSnacks(nutrition.getSnacks());
            existingRecord.setLunch(nutrition.getLunch());
            existingRecord.setPreTraining(nutrition.getPreTraining());
            existingRecord.setAfterTraining(nutrition.getAfterTraining());
            existingRecord.setDinner(nutrition.getDinner());
            existingRecord.setSleepTime(nutrition.getSleepTime());
            return nutritionRepository.save(existingRecord);
        }

        return nutritionRepository.save(nutrition);
    }

    public List<Nutrition> getNutritionByStudentId(Integer studentId) {
        return nutritionRepository.findByStudentIdOrderByLogDateDesc(studentId);
    }

    public Optional<Nutrition> getNutritionByStudentIdAndDate(Integer studentId, LocalDate date) {
        return nutritionRepository.findByStudentIdAndLogDate(studentId, date);
    }

    public List<Nutrition> getAllNutrition() {
        return nutritionRepository.findAllByOrderByLogDateDesc();
    }

    public List<Nutrition> getNutritionByDate(LocalDate date) {
        return nutritionRepository.findByLogDate(date);
    }
}
