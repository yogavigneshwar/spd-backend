package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Attendance;
import com.skipnotAdstudios.SPD_Backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
    public boolean attendanceExists(Integer studentId, LocalDate date) {
    return attendanceRepository.existsByStudentIdAndDate(studentId, date);
    }
    public List<Attendance> getAttendanceByStudentId(Integer studentId) {
    return attendanceRepository.findByStudentId(studentId);
    }
    public long getTodayAttendanceCount() {
    return attendanceRepository.countByDate(LocalDate.now());
    }
    }
