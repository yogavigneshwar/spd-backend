package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Attendance;
import com.skipnotAdstudios.SPD_Backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/testadd")
    public Attendance testAddAttendance() {
        Attendance attendance = new Attendance();

        attendance.setStudentId(4);
        attendance.setCoachId(1);
        attendance.setDate(LocalDate.now());
        attendance.setStatus("PRESENT");
        attendance.setRemarks("On time");

        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
   @PostMapping("/add")
    public Attendance addAttendance(@RequestBody Attendance attendance) {
    boolean exists =
        attendanceService.attendanceExists(
            attendance.getStudentId(),
            LocalDate.now()
        );

    if (exists) {
        Attendance already = new Attendance();
        already.setRemarks("ALREADY_MARKED");
        return already;
    }

    attendance.setDate(LocalDate.now());
    attendance.setStatus("PRESENT");
    attendance.setRemarks("QR Scan");

    return attendanceService.saveAttendance(attendance);
    }
    @GetMapping("/student/{studentId}")
    public List<Attendance> getStudentAttendance(@PathVariable Integer studentId) {
    return attendanceService.getAttendanceByStudentId(studentId);
    }
}