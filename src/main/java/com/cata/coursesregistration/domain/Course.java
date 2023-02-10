package com.cata.coursesregistration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String code;
    private int credits;
    private int semester;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    @OneToMany
    @JoinColumn(name = "class_id")
    private Set<Class> classes = new HashSet<>();

    public Course() {
    }

    public Course(String name, String code, int credits, int semester) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.semester = semester;
    }
}
