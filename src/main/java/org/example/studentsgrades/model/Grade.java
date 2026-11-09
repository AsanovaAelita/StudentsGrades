package org.example.studentsgrades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // при удалении студента его оценки удаляются автоматически
    @NotNull(message = "Нужно указать студента")
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @NotNull(message = "Нужно указать предмет")
    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Min(value = 1, message = "Оценка от 1 до 5")
    @Max(value = 5, message = "Оценка от 1 до 5")
    private int score;

    @NotNull(message = "Дата обязательна")
    @PastOrPresent(message = "Дата не может быть в будущем")
    private LocalDate gradeDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public LocalDate getGradeDate() { return gradeDate; }
    public void setGradeDate(LocalDate gradeDate) { this.gradeDate = gradeDate; }
}
