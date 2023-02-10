package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.*;
import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.CourseDto;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.mappers.CourseMapper;
import com.cata.coursesregistration.mappers.GradeMapper;
import com.cata.coursesregistration.repositories.ClassRepository;
import com.cata.coursesregistration.repositories.CourseRepository;
import com.cata.coursesregistration.repositories.GradeRepository;
import com.cata.coursesregistration.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseServiceTest {

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;


    @Test
    public void getAll() {
        ArrayList<Course> courses = new ArrayList<>(Arrays.asList(
                Course.builder().name("Course 1").build(),
                Course.builder().name("Course 2").build(),
                Course.builder().name("Course 3").build()
        ));

        for( Course c : courses){
            when(courseMapper.toDto(c)).thenReturn(
                    CourseDto.builder().name(c.getName()).build());
        }
        when(courseRepository.findAll())
                .thenReturn(courses);

        assertEquals(3, courseService.getAll().size());
        assertEquals("Course 2", courseService.getAll().get(1).getName());
    }

    @Test
    public void getById() {
        Course course = Course.builder().name("Course 1").build();
        CourseDto courseDto = CourseDto.builder().name("Course 1").build();

        when(courseMapper.toDto(course)).thenReturn(courseDto);
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        assertEquals("Course 1", courseService.getById(1L).getName());
    }

    @Test
    public void saveNew() {
        Course newCourse = Course.builder().name("Course 2").build();
        CourseDto courseDto = CourseDto.builder().name("Course 2").build();

        when(courseMapper.toBo(courseDto)).thenReturn(newCourse);
        when(courseRepository.save(newCourse)).thenReturn(newCourse);

        assertEquals(newCourse, courseService.saveNew(courseDto));
    }

    @Test
    public void updateNotExistingCourse() {
        Course updatedCourse = Course.builder().name("Course 3").build();
        CourseDto courseDto = CourseDto.builder().name("Course 3").build();

        when(courseMapper.toBo(courseDto)).thenReturn(updatedCourse);
        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);

        assertEquals(updatedCourse, courseService.update(courseDto, 1L));
    }

    @Test
    public void updateExistingCourse() {
        Course updatedCourse = Course.builder().name("Course 3").build();
        CourseDto courseDto = CourseDto.builder().name("Course 3").build();

        when(courseRepository.findById(1L)).thenReturn(Optional.of(updatedCourse));
        when(courseRepository.save(updatedCourse)).thenReturn(updatedCourse);

        assertEquals(updatedCourse, courseService.update(courseDto, 1L));
        assertEquals(updatedCourse.getName(),
                courseService.update(courseDto, 1L).getName());
    }

    @Test
    public void delete() {
        Course course = Course.builder().name("Course 1").build();
        when(courseRepository.findById(1L)).thenReturn(Optional.ofNullable(course));
        assertEquals("Course 1 successfully deleted",
                courseService.delete(1L));
    }

}
