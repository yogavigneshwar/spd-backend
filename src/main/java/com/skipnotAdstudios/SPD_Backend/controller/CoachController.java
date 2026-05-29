package com.skipnotAdstudios.SPD_Backend.controller;

import com.skipnotAdstudios.SPD_Backend.entity.Coach;
import com.skipnotAdstudios.SPD_Backend.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
@CrossOrigin(origins = {"http://localhost:5173", "https://spd-frontend.vercel.app"})
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("/testadd")
    public Coach testAddCoach() {
        Coach coach = new Coach();

        coach.setCoachName("Ramesh");
        coach.setMobile("9876543210");
        coach.setPassword("1234");
        coach.setSpecialization("Athletics");

        return coachService.saveCoach(coach);
    }

    @GetMapping("/all")
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

  @GetMapping("/login")
public Coach loginCoach(
    @RequestParam String mobile,
    @RequestParam String password
) {
    List<Coach> coaches = coachService.getAllCoaches();

    for (Coach coach : coaches) {
        System.out.println(
            "Checking: " + coach.getMobile() + " / " + coach.getPassword()
        );

        if (
            coach.getMobile().trim().equals(mobile.trim()) &&
            coach.getPassword().trim().equals(password.trim())
        ) {
            return coach;
        }
    }

    return null;
}
        

      

    @PostMapping("/add")
    public Coach addCoach(@RequestBody Coach coach) {
        return coachService.saveCoach(coach);
    }
}