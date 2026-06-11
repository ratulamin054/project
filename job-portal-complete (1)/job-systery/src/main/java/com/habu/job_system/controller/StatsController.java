package com.habu.job_system.controller;

import com.habu.job_system.repository.ApplicationRepository;
import com.habu.job_system.repository.CompanyRepository;
import com.habu.job_system.repository.JobRepository;
import com.habu.job_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stats")
@CrossOrigin
public class StatsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // ✅ REAL DATABASE থেকে stats
    @GetMapping
    public Map<String, Object> getStats() {
        Map<String, Object> res = new HashMap<>();

        res.put("totalUsers", userRepository.count());
        res.put("totalJobs", jobRepository.count());
        res.put("totalApplications", applicationRepository.count());
        res.put("totalCompanies", companyRepository.count());

        res.put("accepted", applicationRepository.countByStatus("ACCEPTED"));
        res.put("rejected", applicationRepository.countByStatus("REJECTED"));
        res.put("pending", applicationRepository.countByStatus("PENDING"));

        return res;
    }
}