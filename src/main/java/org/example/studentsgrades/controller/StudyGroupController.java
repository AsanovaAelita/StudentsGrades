package org.example.studentsgrades.controller;


import jakarta.validation.Valid;
import org.example.studentsgrades.model.StudyGroup;
import org.example.studentsgrades.repository.StudyGroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class StudyGroupController {

    private final StudyGroupRepository repo;

    public StudyGroupController(StudyGroupRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<StudyGroup> getAll() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudyGroup create(@Valid @RequestBody StudyGroup group) {
        group.setId(null);
        return repo.save(group);
    }
}

