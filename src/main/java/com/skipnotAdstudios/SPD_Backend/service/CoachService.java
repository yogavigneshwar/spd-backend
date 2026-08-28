package com.skipnotAdstudios.SPD_Backend.service;

import com.skipnotAdstudios.SPD_Backend.entity.Coach;
import com.skipnotAdstudios.SPD_Backend.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "coachName"));
    }

    public Coach loginCoach(String mobile, String password) {
        List<Coach> coaches = coachRepository.findAll();

        for (Coach coach : coaches) {
            if (
                coach.getMobile().equals(mobile) &&
                coach.getPassword().equals(password)
            ) {
                return coach;
            }
        }

        return null;
    }
    public long getCoachCount() {
    return coachRepository.count();
    }
}