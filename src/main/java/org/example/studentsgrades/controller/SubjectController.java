package org.example.studentsgrades.controller;

import jakarta.validation.Valid;
import org.example.studentsgrades.model.Subject;
import org.example.studentsgrades.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectRepository repo;

    public SubjectController(SubjectRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Subject> getAll() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subject create(@Valid @RequestBody Subject subject) {
        subject.setId(null);
        return repo.save(subject);
    }
}
