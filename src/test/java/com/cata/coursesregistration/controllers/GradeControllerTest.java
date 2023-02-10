package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.services.GradeService;
import com.cata.coursesregistration.services.StudentService;
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
public class GradeControllerTest {

    @MockBean
    private GradeService gradeService;

    @Autowired
    private GradeController gradeController;

    private static List<GradeDto> gradeDtos;

    @BeforeAll
    public static void gradesSetup() {
        GradeDto grade1 = GradeDto.builder().value(80.9F).build();
        GradeDto grade2 =  GradeDto.builder().value(84.9F).build();
        GradeDto grade3 =  GradeDto.builder().value(89.9F).build();
        gradeDtos = new ArrayList<>(Arrays.asList(grade1, grade2, grade3));
    }

    @DisplayName("Get all Grades")
    @Test
    void assertGetAllTeachers() {
        when(gradeService.getAll()).thenReturn(gradeDtos);
        assertIterableEquals(gradeDtos, gradeController.getAllGrades());
        assertEquals(3, gradeController.getAllGrades().size());
        assertEquals(89.9F, gradeController.getAllGrades().get(2).getValue());
    }

    @DisplayName("Get Grade By Id")
    @Test
    void assertGetTeacherById() {
        when(gradeService.getById(0L)).thenReturn(gradeDtos.get(0));
        assertEquals(gradeDtos.get(0), gradeController.getGrade(0L));
        assertEquals(80.9F, gradeController.getGrade(0L).getValue());
    }

    @DisplayName("Save Grade")
    @Test
    void assertSaveTeacher() {
        Grade grade = Grade.builder().value(89.9F).build();
        when(gradeService.saveNew(gradeDtos.get(1))).thenReturn(grade);
        assertEquals(grade, gradeController.saveGrade(gradeDtos.get(1)));
    }

    @DisplayName("Update Grade")
    @Test
    void assetUpdateTeacher() {
        Grade student = Grade.builder().value(89.9F).build();;
        when(gradeService.update(gradeDtos.get(1), 1L)).thenReturn(student);
        assertEquals(student, gradeController.updateGrade(gradeDtos.get(1), 1L));
    }

    @DisplayName("Delete Grade")
    @Test
    void assertDeleteTeacher() {
        when(gradeService.delete(1L)).thenReturn("Grade 1 successfully deleted");
        assertEquals("Grade 1 successfully deleted", gradeController.deleteGrade(1L));
    }

}
