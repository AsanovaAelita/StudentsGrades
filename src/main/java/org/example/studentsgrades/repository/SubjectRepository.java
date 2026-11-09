package org.example.studentsgrades.repository;

import org.example.studentsgrades.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
