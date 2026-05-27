package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
List<Performance> findByStudentId(Integer studentId);
}