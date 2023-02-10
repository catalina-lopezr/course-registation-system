package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
import com.cata.coursesregistration.mappers.StudentMapper;
import com.cata.coursesregistration.repositories.StudentRepository;
import com.cata.coursesregistration.repositories.TeacherRepository;
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
public class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void getAll() {
        ArrayList<Student> students = new ArrayList<>(Arrays.asList(
                Student.builder()
                        .firstName("Student")
                        .lastName("One")
                        .semester(6)
                        .build(),
                Student.builder()
                        .firstName("Student")
                        .lastName("Two")
                        .semester(7)
                        .build(),
                Student.builder()
                        .firstName("Student")
                        .lastName("Three")
                        .semester(8)
                        .build()
        ));

        for( Student s : students){
            when(studentMapper.toDto(s)).thenReturn(
                    StudentDto.builder().firstName(s.getFirstName())
                            .lastName(s.getLastName()).semester(s.getSemester()).build());
        }
        when(studentRepository.findAll())
                .thenReturn(students);

        assertEquals(3, studentService.getAll().size());
        assertEquals("Two", studentService.getAll().get(1).getLastName());
        assertEquals(7, studentService.getAll().get(1).getSemester());
    }

    @Test
    public void getById() {
        Student student = Student.builder().id(1L).build();
        StudentDto studentDto = StudentDto.builder()
                .firstName("Student")
                .lastName("Three").build();
        when(studentMapper.toDto(student)).thenReturn(studentDto);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        assertEquals("Three", studentService.getById(1L).getLastName());
    }

    @Test
    public void saveNew() {
        Student newStudent = Student.builder().firstName("Edwin")
                .lastName("Montoya")
                .build();
        StudentDto studentDto = StudentDto.builder()
                .firstName("Edwin")
                .lastName("Montoya")
                .build();
        when(studentMapper.toBo(studentDto)).thenReturn(newStudent);
        when(studentRepository.save(newStudent)).thenReturn(newStudent);
        assertEquals(newStudent, studentService.saveNew(studentDto));
    }

    @Test
    public void updateNotExistingStudent() {
        Student updatedStudent = Student.builder().firstName("Edwin")
                .lastName("Montoya")
                .build();
        StudentDto studentDto = StudentDto.builder()
                .firstName("Edwin")
                .lastName("Montoya").build();
        when(studentMapper.toBo(studentDto)).thenReturn(updatedStudent);
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
        assertEquals(updatedStudent, studentService.update(studentDto, 1L));
        System.out.println(studentService.update(studentDto, 1L));
    }

    @Test
    public void updateExistingStudent() {
        Student updatedStudent = Student.builder().firstName("Edwin")
                .lastName("Montoya")
                .build();
        StudentDto studentDto = StudentDto.builder()
                .firstName("Edwin")
                .lastName("Montoya").build();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(updatedStudent));
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
        assertEquals(updatedStudent, studentService.update(studentDto, 1L));
        assertEquals(updatedStudent.getLastName(), studentService.update(studentDto, 1L).getLastName());
    }

    @Test
    public void delete() {
        Student student = Student.builder().firstName("Cata").build();
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));
        assertEquals("Student 1 successfully deleted", studentService.delete(1L));
    }
}
