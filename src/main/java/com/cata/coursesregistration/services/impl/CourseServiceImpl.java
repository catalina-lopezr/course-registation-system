package com.cata.coursesregistration.services.impl;

import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.dtos.CourseDto;
import com.cata.coursesregistration.mappers.CourseMapper;
import com.cata.coursesregistration.repositories.CourseRepository;
import com.cata.coursesregistration.services.CourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courses= courseRepository.findAll();
        List<CourseDto> coursesDtos = new ArrayList<>();
        for (Course course : courses) {
            coursesDtos.add(courseMapper.toDto(course));
        }
        return coursesDtos;
    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        return courseMapper.toDto(course);
    }

    @Override
    public Course saveNew(CourseDto course) {
        Course newCourse = courseMapper.toBo(course);
        newCourse.setCreatedBy("cata");
        newCourse.setCreatedDate(LocalDateTime.now());
        return courseRepository.save(newCourse);
    }

    @Override
    public Course update(CourseDto course, Long id) {
        return courseRepository.findById(id)
                .map(updatedCourse -> {
                    updatedCourse.setName(course.getName());
                    updatedCourse.setCode(course.getCode());
                    updatedCourse.setCredits(course.getCredits());
                    updatedCourse.setSemester(course.getSemester());
                    updatedCourse.setUpdatedBy("cata");
                    updatedCourse.setLastUpdated(LocalDateTime.now());
                    return courseRepository.save(updatedCourse);
                })
                .orElseGet(() -> {
                    Course newCourse = courseMapper.toBo(course);
                    newCourse.setCreatedBy("cata");
                    newCourse.setCreatedDate(LocalDateTime.now());
                    return courseRepository.save(newCourse);
                });
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
