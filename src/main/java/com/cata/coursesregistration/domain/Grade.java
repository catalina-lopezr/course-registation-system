package com.cata.coursesregistration.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float value;

    @ManyToOne
    private Class cl;

    @ManyToOne
    private Student student;

    private String yearSemester;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    public Grade() {
    }

    public Grade(float value, Class cl, Student student, String yearSemester) {
        this.value = value;
        this.cl = cl;
        this.student = student;
        this.yearSemester = yearSemester;
    }
}
