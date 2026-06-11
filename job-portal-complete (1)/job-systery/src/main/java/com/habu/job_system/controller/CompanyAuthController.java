package com.habu.job_system.controller;

import com.habu.job_system.entity.Company;
import com.habu.job_system.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/company")
@CrossOrigin
public class CompanyAuthController {

    @Autowired
    private CompanyRepository repo;

    // REGISTER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Company c) {
        Map<String, Object> res = new HashMap<>();
        if (repo.findByEmail(c.getEmail()).isPresent()) {
            res.put("status", "error");
            res.put("message", "Email already exists");
            return res;
        }
        Company saved = repo.save(c);
        res.put("status", "success");
        res.put("message", "Company registered");
        res.put("company", saved);
        return res;
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Company c) {
        Map<String, Object> res = new HashMap<>();
        Optional<Company> db = repo.findByEmail(c.getEmail());
        if (db.isPresent() && db.get().getPassword().equals(c.getPassword())) {
            res.put("status", "success");
            res.put("company", db.get());
        } else {
            res.put("status", "error");
            res.put("message", "Invalid email or password");
        }
        return res;
    }

    // ✅ ALL COMPANIES (admin dashboard এর জন্য)
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    // ✅ UPDATE COMPANY STATUS (approve/reject/suspend)
    @PutMapping("/{id}/status")
    public Company updateStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<Company> optional = repo.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            company.setRole(status); // role field কে status হিসেবে ব্যবহার করছি
            return repo.save(company);
        }
        return null;
    }

    // ✅ DELETE COMPANY
    @DeleteMapping("/{id}")
    public String deleteCompany(@PathVariable Long id) {
        repo.deleteById(id);
        return "Company deleted";
    }
}