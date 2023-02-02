package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.repositories.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherServiceDBIntegrationTest {

    //Teacher Service test with database integration
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void setupDataBase() {
        jdbc.execute("insert into teacher(id, first_name, last_name) " +
                "values (" + 19L +", 'Eric', 'Roby')");
    }

    @Test
    public void deleteTeacher() {
        Optional<Teacher> deletedTeacher = teacherRepository.findById(19L);
        assertEquals(teacherRepository.findAll().size(), 1);
        assertTrue(deletedTeacher.isPresent(), "Return True");
        teacherRepository.deleteById(19L);
        deletedTeacher = teacherRepository.findById(19L);
        assertFalse(deletedTeacher.isPresent(), "Return False");
    }

    @Sql("/insertData.sql")
    @Test
    public void getAllTeachers() {
        List<Teacher> teachersIterable = teacherRepository.findAll();
        assertEquals(6, teachersIterable.size());
        System.out.println(teachersIterable);
        assertEquals("five", teacherRepository.findByLastName("five").getLastName());
    }

    @Test
    public void saveNewTeacher() {
        Teacher newTeacher = Teacher.builder().firstName("Teacher").lastName("Nine").build();
        teacherRepository.save(newTeacher);
        assertEquals("Nine", teacherRepository.findByLastName("Nine").getLastName());
    }

    @Test
    public void updateTeacher() {
        Optional<Teacher> updatedTeacher = teacherRepository.findById(19L);
        assertTrue(updatedTeacher.isPresent(), "Return true");
        updatedTeacher.get().setLastName("Nine");
        assertEquals("Nine", teacherRepository.findById(19L).get().getLastName());
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute("DELETE FROM teacher");
    }
}
