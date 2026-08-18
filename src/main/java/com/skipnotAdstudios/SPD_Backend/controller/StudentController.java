package com.skipnotAdstudios.SPD_Backend.controller;
import com.skipnotAdstudios.SPD_Backend.entity.Student;
import com.skipnotAdstudios.SPD_Backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.sql.DataSource;



@RestController
@RequestMapping("/student")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://spd-frontend.vercel.app",
    "https://spdsportsportal.in",
    "https://www.spdsportsportal.in"
})
public class StudentController {

    @Autowired
    private StudentService studentService;

   @GetMapping("/testadd")
public Student testAddStudent() {
    Student student = new Student();

    student.setStudentName("Akilesh");
    student.setAge(12);
    student.setParentMobile("9789993635");
    student.setPassword("1234");
    long count = studentService.getAllStudents().size() + 1;
    student.setStudentCode(String.format("SSF-SPD-%03d", count));
    student.setTrainingCategory("BEGINNER");
    student.setCurrentDay(1);
    student.setCoachId(null);
    student.setAcademyId(null);

    return studentService.saveStudent(student);
}
  @GetMapping("/login")
    public Student loginStudent(@RequestParam String parentMobile,
                            @RequestParam String password) {
    return studentService.loginStudent(parentMobile, password);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
    return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {

    student.setCurrentDay(1);

    if (student.getStudentCode() == null || student.getStudentCode().trim().isEmpty()) {
        long count = studentService.getStudentCount() + 1;
        String generatedCode = String.format("SSF-SPD-%03d", count);
        while (studentService.getStudentByCode(generatedCode) != null) {
            count++;
            generatedCode = String.format("SSF-SPD-%03d", count);
        }
        student.setStudentCode(generatedCode);
    }

    return studentService.saveStudent(student);
    }
    @GetMapping("/code")
    public Student getStudentByCode(@RequestParam String studentCode) {
    return studentService.getStudentByCode(studentCode);
    }
    @GetMapping("/teststudent")
    public List<Student> testStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/deduplicate")
    public java.util.Map<String, Object> deduplicateStudents() {
        return studentService.deduplicateStudents();
    }
    @Autowired
private DataSource dataSource;

@GetMapping("/dbcheck")
public String dbCheck() throws Exception {
    return dataSource.getConnection().getMetaData().getURL();
}
}