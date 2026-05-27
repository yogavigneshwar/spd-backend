package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Academy;
import com.skipnotAdstudios.SPD_Backend.repository.AcademyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademyService {

    @Autowired
    private AcademyRepository academyRepository;

    public Academy saveAcademy(Academy academy) {
        return academyRepository.save(academy);
    }

    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }
}