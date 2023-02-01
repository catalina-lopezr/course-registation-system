package com.cata.coursesregistration.repositories;

import com.cata.coursesregistration.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}