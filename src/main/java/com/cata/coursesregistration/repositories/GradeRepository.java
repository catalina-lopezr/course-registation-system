package com.cata.coursesregistration.repositories;

import com.cata.coursesregistration.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}