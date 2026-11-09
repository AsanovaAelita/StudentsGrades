package org.example.studentsgrades.repository;

import org.example.studentsgrades.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // поиск по части ФИО без учёта регистра
    List<Student> findByFullNameContainingIgnoreCase(String part);
}
