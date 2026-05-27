package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompetitionResultRepository extends JpaRepository<CompetitionResult, Integer> {
List<CompetitionResult> findByStudentId(Integer studentId);
}