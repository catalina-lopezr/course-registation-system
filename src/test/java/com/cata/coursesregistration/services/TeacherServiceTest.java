package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import com.cata.coursesregistration.mappers.TeacherMapper;
import com.cata.coursesregistration.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeacherServiceTest {

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void getAll() {
        ArrayList<Teacher> teachers = new ArrayList<>(Arrays.asList(
                new Teacher("Teacher", "one"),
                new Teacher("Teacher", "two"),
                new Teacher("Teacher", "three")));

        for( Teacher t : teachers){
            when(teacherMapper.toDto(t)).thenReturn(
                    new TeacherDto(t.getFirstName(), t.getLastName()));
        }
        when(teacherRepository.findAll())
                .thenReturn(teachers);

        assertEquals(3, teacherService.getAll().size());
        assertEquals("two", teacherService.getAll().get(1).getLastName());
    }

    @Test
    public void getById() {
        Teacher teacher = Teacher.builder().id(1L).build();
        TeacherDto teacherDto = TeacherDto.builder()
                .firstName("Teacher")
                .lastName("Three").build();
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        assertEquals("Three", teacherService.getById(1L).getLastName());
    }

    @Test
    public void saveNew() {
        Teacher newTeacher = new Teacher("Edwin", "Montoya");
        TeacherDto teacherDto = TeacherDto.builder()
                .firstName("Edwin")
                .lastName("Montoya").build();
        when(teacherMapper.toBo(teacherDto)).thenReturn(newTeacher);
        when(teacherRepository.save(newTeacher)).thenReturn(newTeacher);
        assertEquals(newTeacher, teacherService.saveNew(teacherDto));
    }

    @Test
    public void updateNotExistingTeacher() {
        Teacher updatedTeacher = new Teacher("Edwin", "Montoya");
        TeacherDto teacherDto = TeacherDto.builder()
                .firstName("Edwin")
                .lastName("Montoya").build();
        when(teacherMapper.toBo(teacherDto)).thenReturn(updatedTeacher);
        when(teacherRepository.save(updatedTeacher)).thenReturn(updatedTeacher);
        assertEquals(updatedTeacher, teacherService.update(teacherDto, 1L));
        System.out.println(teacherService.update(teacherDto, 1L));
    }

    @Test
    public void updateExistingTeacher() {
        Teacher updatedTeacher = new Teacher("Edwin", "Montoya");
        TeacherDto teacherDto = TeacherDto.builder()
                .firstName("Edwin")
                .lastName("Montoya").build();
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(updatedTeacher));
        when(teacherRepository.save(updatedTeacher)).thenReturn(updatedTeacher);
        assertEquals(updatedTeacher, teacherService.update(teacherDto, 1L));
        assertEquals(updatedTeacher.getLastName(), teacherService.update(teacherDto, 1L).getLastName());
    }

    @Test
    public void deleteTeacherPresent() {
        Teacher teacher = new Teacher("Edwin", "Montoya");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        assertEquals("Teacher 1 successfully deleted",
                teacherService.delete(1L));
    }

//    @Test
//    public void deleteTeacherNotPresent() {
//        assertEquals(new NoSuchElementException(),
//                teacherService.delete(1L));
//    }
}
