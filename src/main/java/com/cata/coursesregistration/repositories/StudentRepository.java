package com.cata.coursesregistration.repositories;

import com.cata.coursesregistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}