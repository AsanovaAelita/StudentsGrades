package org.example.studentsgrades.repository;

import org.example.studentsgrades.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    // Отчёт: JOIN четырёх таблиц + агрегаты AVG и COUNT
    @Query("""
            SELECT gr.name AS groupName,
                   sub.name AS subjectName,
                   ROUND(AVG(g.score), 2) AS avgScore,
                   COUNT(g) AS gradesCount
            FROM Grade g
            JOIN g.student st
            JOIN st.group gr
            JOIN g.subject sub
            GROUP BY gr.name, sub.name
            ORDER BY gr.name, sub.name
            """)
    List<GroupSubjectReport> averageByGroupAndSubject();
}
