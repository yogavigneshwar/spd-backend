package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Performance;
import com.skipnotAdstudios.SPD_Backend.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance savePerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public List<Performance> getAllPerformance() {
        return performanceRepository.findAll();
    }
    public List<Performance> getPerformanceByStudentId(Integer studentId) {
    return performanceRepository.findByStudentId(studentId);
    }
    public long getPerformanceCount() {
    return performanceRepository.count();
    }
}