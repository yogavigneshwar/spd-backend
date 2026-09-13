package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Nutrition;
import com.skipnotAdstudios.SPD_Backend.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/nutrition")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://spd-frontend.vercel.app",
    "https://spdsportsportal.in",
    "https://www.spdsportsportal.in"
})
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @PostMapping("/save")
    public Nutrition saveNutrition(@RequestBody Nutrition nutrition) {
        return nutritionService.saveOrUpdateNutrition(nutrition);
    }

    @GetMapping("/all")
    public List<Nutrition> getAllNutrition() {
        return nutritionService.getAllNutrition();
    }

    @GetMapping("/date/{date}")
    public List<Nutrition> getNutritionByDate(@PathVariable String date) {
        return nutritionService.getNutritionByDate(LocalDate.parse(date));
    }

    @GetMapping("/student/{studentId}")
    public List<Nutrition> getStudentNutrition(@PathVariable Integer studentId) {
        return nutritionService.getNutritionByStudentId(studentId);
    }

    @GetMapping("/student/{studentId}/date/{date}")
    public Optional<Nutrition> getStudentNutritionByDate(@PathVariable Integer studentId, @PathVariable String date) {
        return nutritionService.getNutritionByStudentIdAndDate(studentId, LocalDate.parse(date));
    }
}
