package org.example.studentsgrades.controller;

import jakarta.validation.Valid;
import org.example.studentsgrades.model.Grade;
import org.example.studentsgrades.repository.GradeRepository;
import org.example.studentsgrades.repository.GroupSubjectReport;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeRepository repo;

    public GradeController(GradeRepository repo) {
        this.repo = repo;
    }

    // READ: все оценки
    @GetMapping
    public List<Grade> getAll() {
        return repo.findAll();
    }

    // ОТЧЁТ: средний балл по группам и предметам
    @GetMapping("/report")
    public List<GroupSubjectReport> report() {
        return repo.averageByGroupAndSubject();
    }

    // READ: одна оценка
    @GetMapping("/{id}")
    public Grade getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Оценка не найдена"));
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grade create(@Valid @RequestBody Grade grade) {
        grade.setId(null);
        return repo.save(grade);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Grade update(@PathVariable Long id, @Valid @RequestBody Grade grade) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Оценка не найдена");
        }
        grade.setId(id);
        return repo.save(grade);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Оценка не найдена");
        }
        repo.deleteById(id);
    }
}

