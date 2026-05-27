package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.TrainingProgram;
import com.skipnotAdstudios.SPD_Backend.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    public TrainingProgram saveTrainingProgram(TrainingProgram program) {
        return trainingProgramRepository.save(program);
    }

    public List<TrainingProgram> getAllPrograms() {
        return trainingProgramRepository.findAll();
    }
}