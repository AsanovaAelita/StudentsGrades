package org.example.studentsgrades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "study_groups")
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название группы обязательно")
    @Pattern(regexp = "^[\\p{L}\\p{N}-]{2,20}$",
            message = "Название группы: 2–20 символов (буквы, цифры, дефис)")
    @Column(nullable = false, unique = true)
    private String name;

    @Min(value = 1990, message = "Год начала не раньше 1990")
    @Max(value = 2100, message = "Год начала не позже 2100")
    private int yearStart;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getYearStart() { return yearStart; }
    public void setYearStart(int yearStart) { this.yearStart = yearStart; }
}
