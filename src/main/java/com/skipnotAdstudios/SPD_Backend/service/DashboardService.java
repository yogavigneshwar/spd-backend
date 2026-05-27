package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.repository.AttendanceRepository;
import com.skipnotAdstudios.SPD_Backend.repository.CoachRepository;
import com.skipnotAdstudios.SPD_Backend.repository.CompetitionResultRepository;
import com.skipnotAdstudios.SPD_Backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private CompetitionResultRepository competitionResultRepository;

    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalStudents", studentRepository.count());
        stats.put("totalCoaches", coachRepository.count());
        stats.put("totalAttendance", attendanceRepository.count());
        stats.put("totalResults", competitionResultRepository.count());

        return stats;
    }
}