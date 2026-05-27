package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.service.AttendanceService;
import com.skipnotAdstudios.SPD_Backend.service.CoachService;
import com.skipnotAdstudios.SPD_Backend.service.CompetitionResultService;
import com.skipnotAdstudios.SPD_Backend.service.PerformanceService;
import com.skipnotAdstudios.SPD_Backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoachService coachService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private CompetitionResultService competitionResultService;

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("students", studentService.getStudentCount());
        stats.put("coaches", coachService.getCoachCount());
        stats.put("attendance", attendanceService.getTodayAttendanceCount());
        stats.put("performance", performanceService.getPerformanceCount());
        stats.put("results", competitionResultService.getResultsCount());

        return stats;
    }
}