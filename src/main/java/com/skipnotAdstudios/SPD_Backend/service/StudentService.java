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
        if (parentMobile == null || password == null) {
            return null;
        }
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            if (student.getParentMobile() != null && student.getPassword() != null &&
                student.getParentMobile().trim().equals(parentMobile.trim()) &&
                student.getPassword().trim().equals(password.trim())) {
                
                if (student.getStudentCode() == null || student.getStudentCode().trim().isEmpty()) {
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
        }
        return null;
    }
    public Student getStudentByCode(String studentCode) {
    return studentRepository.findByStudentCode(studentCode);
    }
    public long getStudentCount() {
    return studentRepository.count();
    }
}