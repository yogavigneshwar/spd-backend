package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Integer> {

}