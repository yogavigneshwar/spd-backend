package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Performance;
import com.skipnotAdstudios.SPD_Backend.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://spd-frontend.vercel.app",
    "https://spdsportsportal.in",
    "https://www.spdsportsportal.in"
})
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/all")
    public List<Performance> getAllPerformance() {
        return performanceService.getAllPerformance();
    }

    @PostMapping("/add")
    public Performance addPerformance(@RequestBody Performance performance) {
        return performanceService.savePerformance(performance);
    }
    @GetMapping("/student/{studentId}")
    public List<Performance> getStudentPerformance(@PathVariable Integer studentId) {
    return performanceService.getPerformanceByStudentId(studentId);
    }
}