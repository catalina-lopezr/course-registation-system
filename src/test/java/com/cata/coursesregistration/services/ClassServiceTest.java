package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.*;
import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.ClassDto;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.mappers.ClassMapper;
import com.cata.coursesregistration.mappers.GradeMapper;
import com.cata.coursesregistration.repositories.*;
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
public class ClassServiceTest {

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private ClassRepository classRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private ClassMapper classMapper;

    @Autowired
    private ClassService classService;


    @Test
    public void getAll() {
        Teacher teacher = Teacher.builder().id(11L).build();
        Course course = Course.builder().id(999L).build();
        ArrayList<Class> grades = new ArrayList<>(Arrays.asList(
                Class.builder()
                        .code("ST347-1")
                        .teacher(teacher)
                        .course(course)
                        .build(),
                Class.builder()
                        .code("ST347-2")
                        .teacher(teacher)
                        .course(course)
                        .build(),
                Class.builder()
                        .code("ST347-3")
                        .teacher(teacher)
                        .course(course)
                        .build()
        ));

        for( Class c : grades){
            when(classMapper.toDto(c)).thenReturn(
                    ClassDto.builder()
                            .code(c.getCode())
                            .teacherID(c.getTeacher().getId())
                            .courseID(c.getCourse().getId())
                            .build());
        }
        when(classRepository.findAll())
                .thenReturn(grades);

        assertEquals(3, classService.getAll().size());
        assertEquals("ST347-2", classService.getAll().get(1).getCode());
    }

    @Test
    public void getById() {
        Teacher teacher = Teacher.builder().id(11L).build();
        Course course = Course.builder().id(999L).build();
        Class cl = Class.builder().id(1L).code("ST347-3").teacher(teacher).course(course).build();
        ClassDto classDto = ClassDto.builder().code("ST347-3").teacherID(11L).courseID(999L).build();

        when(classRepository.findById(1L)).thenReturn(Optional.of(cl));
        when(classMapper.toDto(cl)).thenReturn(classDto);


        assertEquals("ST347-3", classService.getById(1L).getCode());
    }

    @Test
    public void saveNew() {
        Teacher teacher = Teacher.builder().id(11L).build();
        Course course = Course.builder().id(999L).build();
        Class newClass = Class.builder().id(1L).code("ST347-3").teacher(teacher).course(course).build();
        ClassDto classDto = ClassDto.builder().teacherID(11L).courseID(999L).build();

        when(classMapper.toBo(classDto)).thenReturn(newClass);
        when(courseRepository.findById(999L)).thenReturn(Optional.ofNullable(course));
        when(teacherRepository.findById(11L)).thenReturn(Optional.ofNullable(teacher));
        when(classRepository.save(newClass)).thenReturn(newClass);

        assertEquals(newClass, classService.saveNew(classDto));
    }

    @Test
    public void updateNotExistingClass() {
        Teacher teacher = Teacher.builder().id(11L).build();
        Course course = Course.builder().id(999L).build();
        Class updatedClass = Class.builder().id(1L).code("ST347-3").teacher(teacher).course(course).build();
        ClassDto classDto = ClassDto.builder().teacherID(11L).courseID(999L).build();

        when(classMapper.toBo(classDto)).thenReturn(updatedClass);
        when(courseRepository.findById(999L)).thenReturn(Optional.ofNullable(course));
        when(teacherRepository.findById(11L)).thenReturn(Optional.ofNullable(teacher));
        when(classRepository.save(updatedClass)).thenReturn(updatedClass);

        assertEquals(updatedClass, classService.update(classDto, 1L));
    }

//    @Test
//    public void updateExistingClass() {
//        Teacher teacher = Teacher.builder().id(11L).build();
//        Course course = Course.builder().id(999L).build();
//        Class cl = Class.builder().id(111L)
//                .code("ST347-3")
//                .teacher(teacher)
//                .course(course).build();
//        Class updatedClass = Class.builder().id(111L)
//                .code("ST347-5")
//                .teacher(teacher)
//                .course(course).build();
//        ClassDto classDto = ClassDto.builder().teacherID(11L).courseID(999L).build();
//
//        when(classRepository.findById(111L)).thenReturn(Optional.of(cl));
//        when(courseRepository.findById(999L)).thenReturn(Optional.ofNullable(course));
//        when(teacherRepository.findById(11L)).thenReturn(Optional.ofNullable(teacher));
//        when(classRepository.save(updatedClass)).thenReturn(updatedClass);
//
//        assertEquals(updatedClass, classService.update(classDto, 1L));
//        assertEquals("ST347-5", classService.update(classDto, 1L).getCode());
//    }

    @Test
    public void delete() {
        Teacher teacher = Teacher.builder().id(11L).build();
        Course course = Course.builder().id(999L).build();
        Class cl = Class.builder().id(1L)
                .code("ST347-3")
                .teacher(teacher)
                .course(course).build();

        when(classRepository.findById(1L)).thenReturn(Optional.ofNullable(cl));
        assertEquals("Class 1 successfully deleted", classService.delete(1L));
    }

}
