package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.dtos.CourseDto;
import com.cata.coursesregistration.services.CourseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;


@TestPropertySource("/application.properties")
@SpringBootTest
public class CourseControllerTest {

    @MockBean
    private CourseService courseService;

    @Autowired
    private CourseController courseController;

    private static List<CourseDto> courseDtos;

    @BeforeAll
    public static void coursesSetup() {
        CourseDto course1 = CourseDto.builder()
                .name("Course 1")
                .code("ST345")
                .credits(3)
                .semester(1)
                .build();
        CourseDto course2 =  CourseDto.builder()
                .name("Course 2")
                .code("ST346")
                .credits(3)
                .semester(2)
                .build();
        CourseDto course3 =  CourseDto.builder()
                .name("Course 3")
                .code("ST347")
                .credits(3)
                .semester(3)
                .build();
        courseDtos = new ArrayList<>(Arrays.asList(course1, course2, course3));
    }

    @DisplayName("Get all Courses")
    @Test
    void assertGetAllCourses() {
        when(courseService.getAll()).thenReturn(courseDtos);
        assertIterableEquals(courseDtos, courseController.getAllCourses());
        assertEquals(3, courseController.getAllCourses().size());
        assertEquals("Course 3",
                courseController.getAllCourses().get(2).getName());
    }

    @DisplayName("Get Course By Id")
    @Test
    void assertGetCourseById() {
        when(courseService.getById(0L)).thenReturn(courseDtos.get(0));
        assertEquals(courseDtos.get(0), courseController.getCourse(0L));
        assertEquals("Course 1",
                courseController.getCourse(0L).getName());
    }

    @DisplayName("Save Course")
    @Test
    void assertSaveCourse() {
        Course course = Course.builder().name("Course 2").build();
        when(courseService.saveNew(courseDtos.get(1))).thenReturn(course);
        assertEquals(course, courseController.saveCourse(courseDtos.get(1)));
    }

    @DisplayName("Update Course")
    @Test
    void assetUpdateCourse() {
        Course student = Course.builder().name("Course 2").build();;
        when(courseService.update(courseDtos.get(1), 1L)).thenReturn(student);
        assertEquals(student, courseController.updateCourse(courseDtos.get(1), 1L));
    }

    @DisplayName("Delete Course")
    @Test
    void assertDeleteCourse() {
        when(courseService.delete(1L)).thenReturn("Course 1 successfully deleted");
        assertEquals("Course 1 successfully deleted", courseController.deleteCourse(1L));
    }

}
