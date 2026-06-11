package com.habu.job_system.controller;

import com.habu.job_system.entity.Job;
import com.habu.job_system.repository.JobRepository;
import com.habu.job_system.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobRepository repo;

    @Autowired
    private JobService service;

    // POST JOB
    @PostMapping("/post")
    public Job postJob(@RequestBody Job job) {
        job.setStatus("ACTIVE");
        Job saved = repo.save(job);
        service.processJob(saved);
        return saved;
    }

    // COMPANY JOBS
    @GetMapping("/company/{name}")
    public List<Job> getCompanyJobs(@PathVariable String name) {
        return repo.findByCompanyName(name);
    }

    // ALL JOBS
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return repo.findAll();
    }

    // ✅ UPDATE JOB STATUS (admin dashboard এর জন্য)
    @PutMapping("/{id}/status")
    public Job updateJobStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Job> optional = repo.findById(id);
        if (optional.isPresent()) {
            Job job = optional.get();
            job.setStatus(status);
            return repo.save(job);
        }
        return null;
    }

    // ✅ DELETE JOB
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        repo.deleteById(id);
        return "Job deleted";
    }
}