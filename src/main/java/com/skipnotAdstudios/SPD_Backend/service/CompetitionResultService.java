package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult;
import com.skipnotAdstudios.SPD_Backend.repository.CompetitionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionResultService {

    @Autowired
    private CompetitionResultRepository competitionResultRepository;

    public CompetitionResult saveResult(CompetitionResult result) {
        return competitionResultRepository.save(result);
    }

    public List<CompetitionResult> getAllResults() {
        return competitionResultRepository.findAll();
    }
    public List<CompetitionResult> getResultsByStudentId(Integer studentId) {
    return competitionResultRepository.findByStudentId(studentId);
    }
    public long getResultsCount() {
    return competitionResultRepository.count();
    }
}