package com.cata.coursesregistration.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;

    @OneToMany
    @JoinColumn(name = "class_id")
    private Set<Class> classes = new HashSet<>();

    public Teacher() {
    }



    public Teacher(String firstName, String lastName, String createdBy, String updatedBy, LocalDateTime createdDate, LocalDateTime lastUpdated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
