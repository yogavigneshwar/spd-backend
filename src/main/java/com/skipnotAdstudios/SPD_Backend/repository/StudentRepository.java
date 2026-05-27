package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByParentMobileAndPassword(String parentMobile, String password);
    long count();
    Student findByStudentCode(String studentCode);
    
}