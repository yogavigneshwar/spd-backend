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

    @Autowired
    private com.skipnotAdstudios.SPD_Backend.repository.AttendanceRepository attendanceRepository;

    @Autowired
    private com.skipnotAdstudios.SPD_Backend.repository.PerformanceRepository performanceRepository;

    @Autowired
    private com.skipnotAdstudios.SPD_Backend.repository.CompetitionResultRepository competitionResultRepository;

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

    @org.springframework.transaction.annotation.Transactional
    public java.util.Map<String, Object> deduplicateStudents() {
        List<Student> allStudents = studentRepository.findAll();
        java.util.Map<String, List<Student>> groups = new java.util.HashMap<>();
        
        for (Student student : allStudents) {
            String name = student.getStudentName() != null ? student.getStudentName().trim().toLowerCase() : "";
            String parentMobile = student.getParentMobile() != null ? student.getParentMobile().trim() : "";
            int age = student.getAge() != null ? student.getAge() : 0;
            
            String key = name + "_" + parentMobile + "_" + age;
            groups.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(student);
        }
        
        int duplicatesFound = 0;
        int studentsDeleted = 0;
        int attendanceMerged = 0;
        int performanceMerged = 0;
        int resultsMerged = 0;
        
        List<String> mergedDetails = new java.util.ArrayList<>();
        
        for (java.util.Map.Entry<String, List<Student>> entry : groups.entrySet()) {
            List<Student> group = entry.getValue();
            if (group.size() > 1) {
                group.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
                Student master = group.get(0);
                
                duplicatesFound += (group.size() - 1);
                
                for (int i = 1; i < group.size(); i++) {
                    Student duplicate = group.get(i);
                    
                    // 1. Merge Attendance
                    List<com.skipnotAdstudios.SPD_Backend.entity.Attendance> attendanceList = attendanceRepository.findByStudentId(duplicate.getId());
                    for (com.skipnotAdstudios.SPD_Backend.entity.Attendance att : attendanceList) {
                        att.setStudentId(master.getId());
                        attendanceRepository.save(att);
                        attendanceMerged++;
                    }
                    
                    // 2. Merge Performance
                    List<com.skipnotAdstudios.SPD_Backend.entity.Performance> perfList = performanceRepository.findByStudentId(duplicate.getId());
                    for (com.skipnotAdstudios.SPD_Backend.entity.Performance perf : perfList) {
                        perf.setStudentId(master.getId());
                        performanceRepository.save(perf);
                        performanceMerged++;
                    }
                    
                    // 3. Merge Competition Results
                    List<com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult> compList = competitionResultRepository.findByStudentId(duplicate.getId());
                    for (com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult comp : compList) {
                        comp.setStudentId(master.getId());
                        competitionResultRepository.save(comp);
                        resultsMerged++;
                    }
                    
                    // 4. Delete duplicate student
                    studentRepository.delete(duplicate);
                    studentsDeleted++;
                    
                    mergedDetails.add("Merged Student: " + duplicate.getStudentName() + " (ID: " + duplicate.getId() + " Code: " + duplicate.getStudentCode() + ") into Master (ID: " + master.getId() + " Code: " + master.getStudentCode() + ")");
                }
            }
        }
        
        java.util.Map<String, Object> result = new java.util.HashMap<>();
        result.put("status", "SUCCESS");
        result.put("duplicatesFound", duplicatesFound);
        result.put("studentsDeleted", studentsDeleted);
        result.put("attendanceMerged", attendanceMerged);
        result.put("performanceMerged", performanceMerged);
        result.put("resultsMerged", resultsMerged);
        result.put("details", mergedDetails);
        return result;
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteStudent(Integer id) {
        List<com.skipnotAdstudios.SPD_Backend.entity.Attendance> attendanceList = attendanceRepository.findByStudentId(id);
        attendanceRepository.deleteAll(attendanceList);

        List<com.skipnotAdstudios.SPD_Backend.entity.Performance> performanceList = performanceRepository.findByStudentId(id);
        performanceRepository.deleteAll(performanceList);

        List<com.skipnotAdstudios.SPD_Backend.entity.CompetitionResult> resultsList = competitionResultRepository.findByStudentId(id);
        competitionResultRepository.deleteAll(resultsList);

        studentRepository.deleteById(id);
    }
}