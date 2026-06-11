package com.habu.job_system.config;

import com.habu.job_system.entity.*;
import com.habu.job_system.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(
            UserRepository userRepo,
            CompanyRepository companyRepo,
            JobRepository jobRepo,
            ApplicationRepository appRepo
    ) {
        return args -> {

            // ── Admin user ──────────────────────────────────────────
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@jobportal.com");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            admin.setPhone("01700000000");
            admin.setSkills("Management");
            admin.setEducation("MBA");
            admin.setExperience("10 years");
            userRepo.save(admin);

            // ── Demo users ──────────────────────────────────────────
            User u1 = new User();
            u1.setName("Alice Rahman");
            u1.setEmail("alice@example.com");
            u1.setPassword("pass123");
            u1.setRole("USER");
            u1.setPhone("01711111111");
            u1.setSkills("Java, Spring Boot, React");
            u1.setEducation("BSc CSE");
            u1.setExperience("2 years");
            userRepo.save(u1);

            User u2 = new User();
            u2.setName("Bob Khan");
            u2.setEmail("bob@example.com");
            u2.setPassword("pass123");
            u2.setRole("USER");
            u2.setPhone("01722222222");
            u2.setSkills("Python, Machine Learning");
            u2.setEducation("MSc CS");
            u2.setExperience("3 years");
            userRepo.save(u2);

            // ── Demo companies ──────────────────────────────────────
            Company c1 = new Company();
            c1.setName("TechCorp BD");
            c1.setEmail("techcorp@example.com");
            c1.setPassword("company123");
            c1.setRole("COMPANY");
            companyRepo.save(c1);

            Company c2 = new Company();
            c2.setName("DataWave Ltd");
            c2.setEmail("datawave@example.com");
            c2.setPassword("company123");
            c2.setRole("COMPANY");
            companyRepo.save(c2);

            Company c3 = new Company();
            c3.setName("CloudNine Systems");
            c3.setEmail("cloudnine@example.com");
            c3.setPassword("company123");
            c3.setRole("COMPANY");
            companyRepo.save(c3);

            // ── Demo jobs ───────────────────────────────────────────
            String[][] jobs = {
                {"Senior Java Developer", "5+ years Spring Boot experience required. Work on microservices architecture.", "TechCorp BD", "Full-time", "60,000 BDT", "Dhaka"},
                {"React Frontend Engineer", "Build modern UIs with React, TypeScript, and Tailwind CSS.", "TechCorp BD", "Full-time", "50,000 BDT", "Dhaka"},
                {"ML Engineer", "Design and deploy machine learning pipelines at scale.", "DataWave Ltd", "Full-time", "70,000 BDT", "Chittagong"},
                {"Data Analyst", "Analyze large datasets and create actionable business insights.", "DataWave Ltd", "Part-time", "35,000 BDT", "Remote"},
                {"DevOps Engineer", "Manage CI/CD pipelines, AWS infrastructure, and Kubernetes clusters.", "CloudNine Systems", "Full-time", "65,000 BDT", "Dhaka"},
                {"Python Developer", "Develop backend services using FastAPI and Django.", "CloudNine Systems", "Contract", "45,000 BDT", "Remote"},
            };

            for (String[] j : jobs) {
                Job job = new Job();
                job.setTitle(j[0]);
                job.setDescription(j[1]);
                job.setCompanyName(j[2]);
                job.setType(j[3]);
                job.setSalary(j[4]);
                job.setLocation(j[5]);
                job.setStatus("ACTIVE");
                jobRepo.save(job);
            }

            // ── Demo applications ───────────────────────────────────
            Application a1 = new Application();
            a1.setUserName("Alice Rahman");
            a1.setUserEmail("alice@example.com");
            a1.setJobTitle("Senior Java Developer");
            a1.setCompanyName("TechCorp BD");
            a1.setStatus("PENDING");
            appRepo.save(a1);

            Application a2 = new Application();
            a2.setUserName("Alice Rahman");
            a2.setUserEmail("alice@example.com");
            a2.setJobTitle("React Frontend Engineer");
            a2.setCompanyName("TechCorp BD");
            a2.setStatus("ACCEPTED");
            appRepo.save(a2);

            Application a3 = new Application();
            a3.setUserName("Bob Khan");
            a3.setUserEmail("bob@example.com");
            a3.setJobTitle("ML Engineer");
            a3.setCompanyName("DataWave Ltd");
            a3.setStatus("REJECTED");
            appRepo.save(a3);

            System.out.println("✅ Demo data seeded successfully!");
            System.out.println("   Admin: admin@jobportal.com / admin123");
            System.out.println("   User:  alice@example.com / pass123");
            System.out.println("   Company: techcorp@example.com / company123");
        };
    }
}
