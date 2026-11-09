package org.example.studentsgrades.repository;

// одна строка отчёта: группа, предмет, средний балл, количество оценок
public interface GroupSubjectReport {
    String getGroupName();
    String getSubjectName();
    Double getAvgScore();
    Long getGradesCount();
}
