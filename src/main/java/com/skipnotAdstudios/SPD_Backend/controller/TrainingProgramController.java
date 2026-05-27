package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.TrainingProgram;
import com.skipnotAdstudios.SPD_Backend.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingProgramController {

    @Autowired
    private TrainingProgramService trainingProgramService;

    @GetMapping("/testadd")
    public TrainingProgram testAddTraining() {
        TrainingProgram program = new TrainingProgram();

        program.setCategory("BEGINNER");
        program.setDayNumber(1);
        program.setTrainingTitle("Sprint Warmup");
        program.setDescription("10 min warmup + agility drills");
        program.setNutritionNote("Protein + hydration");

        return trainingProgramService.saveTrainingProgram(program);
    }

    @GetMapping("/all")
    public List<TrainingProgram> getAllPrograms() {
        return trainingProgramService.getAllPrograms();
    }
}