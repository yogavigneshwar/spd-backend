package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Attendance;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    boolean existsByStudentIdAndDate(Integer studentId, LocalDate date);
    List<Attendance> findByStudentId(Integer studentId);
    long countByDate(LocalDate date);
}
