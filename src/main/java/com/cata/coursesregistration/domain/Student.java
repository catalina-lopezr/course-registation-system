package com.cata.coursesregistration.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int semester;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    @ManyToMany(mappedBy = "students")
    private Set<Class> classes = new HashSet<>();

    @OneToMany
    private List<Grade> grades;

    public Student() {
    }

    public Student(String firstName, String lastName, int semester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
    }
}
