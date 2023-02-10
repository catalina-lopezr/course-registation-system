package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> getAll();

    StudentDto getById(Long id);

    Student saveNew(StudentDto student);

    Student update(StudentDto student, Long id);

    String delete(Long id);

    Student addClasses(Long id, List<Long> classes);
}
