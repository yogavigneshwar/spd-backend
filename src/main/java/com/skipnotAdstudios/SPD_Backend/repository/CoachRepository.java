package com.skipnotAdstudios.SPD_Backend.repository;

import com.skipnotAdstudios.SPD_Backend.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {

    @Query("SELECT c FROM Coach c WHERE c.mobile = :mobile AND c.password = :password")
    Coach loginCoach(@Param("mobile") String mobile, @Param("password") String password);
    long count();
}