package org.example.studentsgrades.controller;

import jakarta.validation.Valid;
import org.example.studentsgrades.model.Student;
import org.example.studentsgrades.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // READ: все студенты
    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    // READ: один студент
    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Студент не найден"));
    }

    // ПОИСК: /api/students/search?name=ива
    @GetMapping("/search")
    public List<Student> search(@RequestParam String name) {
        if (name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Запрос не может быть пустым");
        }
        return repo.findByFullNameContainingIgnoreCase(name.trim());
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student) {
        student.setId(null);
        return repo.save(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student student) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Студент не найден");
        }
        student.setId(id);
        return repo.save(student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Студент не найден");
        }
        repo.deleteById(id);
    }
}

