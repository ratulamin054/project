package com.habu.job_system.controller;

import com.habu.job_system.entity.Application;
import com.habu.job_system.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
@CrossOrigin
public class ApplicationController {

    @Autowired
    private ApplicationRepository repository;

    // APPLY JOB
    @PostMapping("/apply")
    public Application apply(@RequestBody Application app) {
        return repository.save(app);
    }

    // ALL APPLICATIONS
    @GetMapping("/all")
    public List<Application> all() {
        return repository.findAll();
    }

    // USER APPLICATIONS
    @GetMapping("/user/{email}")
    public List<Application> userApplications(@PathVariable String email) {
        return repository.findByUserEmail(email);
    }

    // ✅ ACCEPT / REJECT APPLICATION (admin dashboard এর জন্য)
    // dashboard call করে: PUT /application/accept/{id} বা /application/reject/{id}
    @PutMapping("/{action}/{id}")
    public Application updateStatus(@PathVariable String action, @PathVariable Long id) {
        Optional<Application> optional = repository.findById(id);
        if (optional.isPresent()) {
            Application app = optional.get();
            if (action.equalsIgnoreCase("accept")) {
                app.setStatus("ACCEPTED");
            } else if (action.equalsIgnoreCase("reject")) {
                app.setStatus("REJECTED");
            }
            return repository.save(app);
        }
        return null;
    }

    // ✅ DELETE APPLICATION
    @DeleteMapping("/{id}")
    public String deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
        return "Application deleted";
    }
}