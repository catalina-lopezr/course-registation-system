package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.dtos.CourseDto;
import com.cata.coursesregistration.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseDto getCourse(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    public Course saveCourse(@RequestBody CourseDto course) {
        return courseService.saveNew(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@RequestBody CourseDto course, @PathVariable Long id) {
        return courseService.update(course, id);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        return courseService.delete(id);
    }
}
