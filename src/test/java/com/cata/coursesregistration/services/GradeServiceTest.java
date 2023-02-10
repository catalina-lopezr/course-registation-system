package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.mappers.GradeMapper;
import com.cata.coursesregistration.repositories.ClassRepository;
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
public class GradeServiceTest {

    @MockBean
    private GradeRepository gradeRepository;

    @MockBean
    private ClassRepository classRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private GradeMapper gradeMapper;

    @Autowired
    private GradeService gradeService;


    @Test
    public void getAll() {
        Class cl = Class.builder().id(11L).build();
        Student student = Student.builder().id(999L).build();
        ArrayList<Grade> grades = new ArrayList<>(Arrays.asList(
                Grade.builder().value(90).cl(cl).student(student).build(),
                Grade.builder().value(99).cl(cl).student(student).build(),
                Grade.builder().value(99.9F).cl(cl).student(student).build()
        ));

        for( Grade g : grades){
            when(gradeMapper.toDto(g)).thenReturn(
                    GradeDto.builder().value(g.getValue()).build());
        }
        when(gradeRepository.findAll())
                .thenReturn(grades);

        assertEquals(3, gradeService.getAll().size());
        assertEquals(99, gradeService.getAll().get(1).getValue());
    }

    @Test
    public void getById() {
        Class cl = Class.builder().id(11L).build();
        Student student = Student.builder().id(999L).build();
        Grade grade = Grade.builder().id(1L).cl(cl).student(student).value(99).build();
        GradeDto gradeDto = GradeDto.builder().clID(11L).studentID(999L).value(99).value(99).build();

        when(gradeMapper.toDto(grade)).thenReturn(gradeDto);
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        assertEquals(99, gradeService.getById(1L).getValue());
    }

    @Test
    public void saveNew() {
        Class cl = Class.builder().id(1L).build();
        Student student = Student.builder().id(1L).build();
        Grade newGrade = Grade.builder().cl(cl).student(student).value(98).build();
        GradeDto gradeDto = GradeDto.builder().clID(1L).studentID(1L).value(98).build();

        when(gradeMapper.toBo(gradeDto)).thenReturn(newGrade);
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        when(classRepository.findById(1L)).thenReturn(Optional.ofNullable(cl));
        when(gradeRepository.save(newGrade)).thenReturn(newGrade);

        assertEquals(newGrade, gradeService.saveNew(gradeDto));
    }

    @Test
    public void updateNotExistingGrade() {
        Teacher teacher = Teacher.builder().id(802L).build();
        Class cl = Class.builder().id(1L).teacher(teacher).build();
        Student student = Student.builder().id(1L).build();
        Grade updatedGrade = Grade.builder().id(999L).value(77).cl(cl).student(student).build();
        GradeDto gradeDto = GradeDto.builder().value(77).studentID(1L).clID(1L).build();

        when(gradeMapper.toBo(gradeDto)).thenReturn(updatedGrade);
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        when(classRepository.findById(1L)).thenReturn(Optional.ofNullable(cl));
        when(gradeRepository.save(updatedGrade)).thenReturn(updatedGrade);

        assertEquals(updatedGrade, gradeService.update(gradeDto, 1L));
    }

    @Test
    public void updateExistingGrade() {
        Teacher teacher = Teacher.builder().id(802L).build();
        Class cl = Class.builder().id(1L).teacher(teacher).build();
        Student student = Student.builder().id(1L).build();
        Grade updatedGrade = Grade.builder().value(77).cl(cl).student(student).build();
        GradeDto gradeDto = GradeDto.builder().value(77).studentID(1L).clID(1L).build();

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(updatedGrade));
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        when(classRepository.findById(1L)).thenReturn(Optional.ofNullable(cl));
        when(gradeRepository.save(updatedGrade)).thenReturn(updatedGrade);

        assertEquals(updatedGrade, gradeService.update(gradeDto, 1L));
        assertEquals(updatedGrade.getValue(), gradeService.update(gradeDto, 1L).getValue());
    }

    @Test
    public void delete() {
        Teacher teacher = Teacher.builder().id(802L).build();
        Class cl = Class.builder().id(1L).teacher(teacher).build();
        Student student = Student.builder().id(1L).build();
        Grade grade = Grade.builder().value(77).cl(cl).student(student).build();
        when(gradeRepository.findById(1L)).thenReturn(Optional.ofNullable(grade));
        assertEquals("Grade 1 successfully deleted", gradeService.delete(1L));
    }

}
