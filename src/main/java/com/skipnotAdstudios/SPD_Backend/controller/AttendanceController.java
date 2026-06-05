package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Attendance;
import com.skipnotAdstudios.SPD_Backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.skipnotAdstudios.SPD_Backend.entity.Student;
import com.skipnotAdstudios.SPD_Backend.repository.StudentRepository;

@RestController
@RequestMapping("/attendance")
@CrossOrigin (origins = {
    "http://localhost:5173" ,
    "https://spd-frontend.vercel.app"
}
)
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private StudentRepository studentRepository;

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
public Attendance addAttendance(@RequestBody Map<String, String> body) {

    String studentCode = body.get("studentCode");

    Student student = studentRepository.findByStudentCode(studentCode);

    if (student == null) {
        Attendance error = new Attendance();
        error.setRemarks("STUDENT_NOT_FOUND");
        return error;
    }

    boolean exists =
            attendanceService.attendanceExists(
                    student.getId(),
                    LocalDate.now()
            );

    if (exists) {
        Attendance already = new Attendance();
        already.setRemarks("ALREADY_MARKED");
        return already;
    }

    Attendance attendance = new Attendance();

    attendance.setStudentId(student.getId());

    // Temporary coach id
    attendance.setCoachId(1);

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