package org.example.studentsgrades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ФИО обязательно")
    @Size(min = 2, max = 100, message = "ФИО: 2–100 символов")
    @Column(nullable = false)
    private String fullName;

    @Email(message = "Неверный формат email")
    @Column(unique = true)
    private String email;

    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @NotNull(message = "Нужно указать группу")
    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private StudyGroup group;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public StudyGroup getGroup() { return group; }
    public void setGroup(StudyGroup group) { this.group = group; }
}
