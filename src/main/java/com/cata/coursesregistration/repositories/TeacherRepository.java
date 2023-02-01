package com.cata.coursesregistration.repositories;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public Teacher findByFirstName(String firstName);
}