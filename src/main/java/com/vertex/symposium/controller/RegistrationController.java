package com.vertex.symposium.controller;

import com.vertex.symposium.model.Registration;
import com.vertex.symposium.repository.RegistrationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationRepository repo;

    public RegistrationController(RegistrationRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Registration create(@RequestBody Registration registration) {
        return repo.save(registration);
    }

    @GetMapping
    public List<Registration> list() {
        return repo.findAll();
    }
}
