package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Attendance;
import com.skipnotAdstudios.SPD_Backend.entity.Student;
import com.skipnotAdstudios.SPD_Backend.repository.AttendanceRepository;
import com.skipnotAdstudios.SPD_Backend.repository.StudentRepository;
import com.skipnotAdstudios.SPD_Backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://spd-frontend.vercel.app",
    "https://spdsportsportal.in",
    "https://www.spdsportsportal.in"
})
public class AttendanceController {

    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/testadd")
    public Attendance testAddAttendance() {
        Attendance attendance = new Attendance();
        attendance.setStudentId(4);
        attendance.setCoachId(1);
        attendance.setDate(LocalDate.now(IST_ZONE));
        attendance.setScannedAt(LocalDateTime.now(IST_ZONE));
        attendance.setStatus("PRESENT");
        attendance.setRemarks("On time");

        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/all")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @PostMapping("/add")
    public Attendance addAttendance(@RequestBody Map<String, String> body) {
        String studentCode = body.get("studentCode");

        Student student = studentRepository.findByStudentCode(studentCode);

        if (student == null) {
            Attendance error = new Attendance();
            error.setRemarks("STUDENT_NOT_FOUND");
            return error;
        }

        boolean existsInLastHour = attendanceService.attendanceExistsInLastHour(student.getId());

        if (existsInLastHour) {
            Attendance already = new Attendance();
            already.setRemarks("ALREADY_MARKED");
            return already;
        }

        Attendance attendance = new Attendance();
        attendance.setStudentId(student.getId());

        // Temporary coach id
        attendance.setCoachId(1);

        attendance.setDate(LocalDate.now(IST_ZONE));
        attendance.setScannedAt(LocalDateTime.now(IST_ZONE));
        attendance.setStatus("PRESENT");
        attendance.setRemarks("QR Scan");

        return attendanceService.saveAttendance(attendance);
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getStudentAttendance(@PathVariable Integer studentId) {
        return attendanceService.getAttendanceByStudentId(studentId);
    }

    @GetMapping("/fix-timezones")
    public Map<String, Object> fixHistoricalTimezones() {
        List<Attendance> all = attendanceRepository.findAll();
        int fixedCount = 0;
        for (Attendance att : all) {
            if (att.getScannedAt() != null) {
                // If scannedAt was recorded in UTC (earlier than 5:00 AM, e.g., 00:xx:xx or 01:xx:xx),
                // it was generated on Railway UTC instead of IST. We add 5 hours 30 mins to convert to IST.
                if (att.getScannedAt().getHour() < 5) {
                    att.setScannedAt(att.getScannedAt().plusHours(5).plusMinutes(30));
                    attendanceRepository.save(att);
                    fixedCount++;
                }
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("status", "SUCCESS");
        res.put("totalRecords", all.size());
        res.put("recordsFixed", fixedCount);
        return res;
    }
}