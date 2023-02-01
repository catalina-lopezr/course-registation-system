package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.dtos.CourseDto;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseDto> getAll();
    CourseDto getById(Long id);
    Course saveNew(CourseDto course);
    Course update(CourseDto course, Long id);
    void delete(Long id);
}
