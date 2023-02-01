package com.cata.coursesregistration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    private Course course;

    @ManyToMany
    @JoinTable(name = "student_class", joinColumns = @JoinColumn(name = "class_id"),
                inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @OneToMany
    private List<Grade> grades;

    public Class(String code, LocalDate startDate, LocalDate finishDate, LocalTime startTime, LocalTime finishTime, Teacher teacher, Course course) {
        this.code = code;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.teacher = teacher;
        this.course = course;
    }
}
