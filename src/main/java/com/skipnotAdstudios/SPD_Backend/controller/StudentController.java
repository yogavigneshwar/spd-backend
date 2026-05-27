package com.skipnotAdstudios.SPD_Backend.controller;
import com.skipnotAdstudios.SPD_Backend.entity.Student;
import com.skipnotAdstudios.SPD_Backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:5173")
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

    return studentService.saveStudent(student);
    }
    @GetMapping("/code")
    public Student getStudentByCode(@RequestParam String studentCode) {
    return studentService.getStudentByCode(studentCode);
    }
}