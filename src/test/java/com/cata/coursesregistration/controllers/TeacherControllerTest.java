package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import com.cata.coursesregistration.services.TeacherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestPropertySource("/application.properties")
@SpringBootTest
public class TeacherControllerTest {

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private TeacherController teacherController;

    private static List<TeacherDto> teacherDtos;

    @BeforeAll
    public static void teachersSetup() {
        TeacherDto teacher1 = new TeacherDto("Daniel", "Correa");
        TeacherDto teacher2 = new TeacherDto("Carlos", "Montoya");
        TeacherDto teacher3 = new TeacherDto("Edwin", "Montoya");
        teacherDtos = new ArrayList<>(Arrays.asList(teacher1, teacher2, teacher3));
    }

    @DisplayName("Get all Teachers")
    @Test
    void assertGetAllTeachers() {
        when(teacherService.getAll()).thenReturn(teacherDtos);
        assertIterableEquals(teacherDtos, teacherController.getAllTeachers());
        assertEquals(3, teacherController.getAllTeachers().size());
        assertEquals("Montoya", teacherController.getAllTeachers().get(2).getLastName());
    }

    @DisplayName("Get Teacher By Id")
    @Test
    void assertGetTeacherById() {
        when(teacherService.getById(0L)).thenReturn(teacherDtos.get(0));
        assertEquals(teacherDtos.get(0), teacherController.getTeacher(0L));
        assertEquals("Daniel", teacherController.getTeacher(0L).getFirstName());
    }

    @DisplayName("Save Teacher")
    @Test
    void assertSaveTeacher() {
        Teacher teacher = new Teacher("Daniel", "Correa", "cata",
                        null, LocalDateTime.now(), null);
        when(teacherService.saveNew(teacherDtos.get(1))).thenReturn(teacher);
        assertEquals(teacher, teacherController.saveTeacher(teacherDtos.get(1)));
    }

    @DisplayName("Update Teacher")
    @Test
    void assetUpdateTeacher() {
        Teacher teacher = new Teacher("Carlos", "Correa", "cata",
                "cata", LocalDateTime.now(), LocalDateTime.now());
        when(teacherService.update(teacherDtos.get(1), 1L)).thenReturn(teacher);
        assertEquals(teacher, teacherController.updateTeacher(teacherDtos.get(1), 1L));
    }

    @DisplayName("Delete Teacher")
    @Test
    void assertDeleteTeacher() {
        when(teacherService.delete(1L)).thenReturn("Teacher 1 successfully deleted");
        assertEquals("Teacher 1 successfully deleted", teacherController.deleteTeacher(1L));
    }

}
