package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Academy;
import com.skipnotAdstudios.SPD_Backend.service.AcademyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @PostMapping("/add")
    public Academy addAcademy(@RequestBody Academy academy) {
        return academyService.saveAcademy(academy);
    }

    @GetMapping("/all")
    public List<Academy> getAllAcademies() {
        return academyService.getAllAcademies();
    }

    @GetMapping("/testadd")
public Academy testAddAcademy() {
    Academy academy = new Academy();
    academy.setAcademyName("Smart Sports Foundation");
    academy.setOwnerName("Owner");
    return academyService.saveAcademy(academy);
     }
     
}