package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
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
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private StudentController studentController;

    private static List<StudentDto> studentDtos;

    @BeforeAll
    public static void studentsSetup() {
        StudentDto student1 = StudentDto.builder().firstName("Catalina").lastName("Lopez").semester(5).build();
        StudentDto student2 =  StudentDto.builder().firstName("Federico").lastName("Perez").semester(9).build();
        StudentDto student3 =  StudentDto.builder().firstName("Andres").lastName("Chaves").semester(5).build();
        studentDtos = new ArrayList<>(Arrays.asList(student1, student2, student3));
    }

    @DisplayName("Get all Students")
    @Test
    void assertGetAllTeachers() {
        when(studentService.getAll()).thenReturn(studentDtos);
        assertIterableEquals(studentDtos, studentController.getAllStudents());
        assertEquals(3, studentController.getAllStudents().size());
        assertEquals("Chaves", studentController.getAllStudents().get(2).getLastName());
    }

    @DisplayName("Get Student By Id")
    @Test
    void assertGetTeacherById() {
        when(studentService.getById(0L)).thenReturn(studentDtos.get(0));
        assertEquals(studentDtos.get(0), studentController.getStudent(0L));
        assertEquals("Catalina", studentController.getStudent(0L).getFirstName());
    }

    @DisplayName("Save Student")
    @Test
    void assertSaveTeacher() {
        Student student = Student.builder().id(999L).firstName("Federico").lastName("Perez").build();
        when(studentService.saveNew(studentDtos.get(1))).thenReturn(student);
        assertEquals(student, studentController.saveStudent(studentDtos.get(1)));
    }

    @DisplayName("Update Student")
    @Test
    void assetUpdateTeacher() {
        Student student = Student.builder().id(999L).firstName("Federico").lastName("Perez").build();
        when(studentService.update(studentDtos.get(1), 1L)).thenReturn(student);
        assertEquals(student, studentController.updateStudent(studentDtos.get(1), 1L));
    }

    @DisplayName("Delete Student")
    @Test
    void assertDeleteTeacher() {
        when(studentService.delete(1L)).thenReturn("Student 1 successfully deleted");
        assertEquals("Student 1 successfully deleted", studentController.deleteStudent(1L));
    }

}
