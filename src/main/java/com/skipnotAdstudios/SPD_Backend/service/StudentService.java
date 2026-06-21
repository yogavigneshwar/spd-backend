package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Student;
import com.skipnotAdstudios.SPD_Backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student loginStudent(String parentMobile, String password) {
        Student student = studentRepository.findByParentMobileAndPassword(parentMobile, password);
        if (student != null && (student.getStudentCode() == null || student.getStudentCode().trim().isEmpty())) {
            long count = studentRepository.count() + 1;
            String generatedCode = String.format("SSF-SPD-%03d", count);
            while (studentRepository.findByStudentCode(generatedCode) != null) {
                count++;
                generatedCode = String.format("SSF-SPD-%03d", count);
            }
            student.setStudentCode(generatedCode);
            student = studentRepository.save(student);
        }
        return student;
    }
    public Student getStudentByCode(String studentCode) {
    return studentRepository.findByStudentCode(studentCode);
    }
    public long getStudentCount() {
    return studentRepository.count();
    }
}