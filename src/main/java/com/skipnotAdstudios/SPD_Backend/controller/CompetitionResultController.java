package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult;
import com.skipnotAdstudios.SPD_Backend.service.CompetitionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/results")
@CrossOrigin(origins = "http://localhost:5173")
public class CompetitionResultController {

    @Autowired
    private CompetitionResultService competitionResultService;

    @GetMapping("/all")
    public List<CompetitionResult> getAllResults() {
        return competitionResultService.getAllResults();
    }

    @PostMapping("/add")
    public CompetitionResult addResult(@RequestBody CompetitionResult result) {
        return competitionResultService.saveResult(result);
    }
    @GetMapping("/student/{studentId}")
    public List<CompetitionResult> getStudentResults(@PathVariable Integer studentId) {
    return competitionResultService.getResultsByStudentId(studentId);
    }
}