package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestPropertySource("/application.properties")
//@SpringBootTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherServiceDBIntegrationTest {

    //Teacher Service test with database integration
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TestEntityManager entityManager;

//    @Autowired
//    private TeacherService teacherService;

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
//        assertTrue(deletedTeacher.isPresent(), "Return True");
//        teacherRepository.deleteById(19L);
//        deletedTeacher = teacherRepository.findById(19L);
//        assertFalse(deletedTeacher.isPresent(), "Return False");
    }

//    @Sql("/insertData.sql")
//    @Test
//    public void getAllTeachers() {
//        Iterable<Teacher> teachersIterable = teacherRepository.findAll();
//        List<Teacher> teachers = new ArrayList<>();
//        for (Teacher teacher : teachersIterable){
//            teachers.add(teacher);
//        }
//        assertEquals(6, teachers.size());
//    }
//
//    @Test
//    public void saveNewTeacher() {
//        TeacherDto teacherDto = new TeacherDto("Federico", "Perez");
//        teacherService.saveNew(teacherDto);
//        Teacher teacher = teacherRepository.findByFirstName("Federico");
//        assertEquals("Federico", teacher.getFirstName());
//    }
//
//    @Test
//    public void updateTeacher() {
//        TeacherDto teacherDto = new TeacherDto("Federico", "Perez");
//        teacherService.update(teacherDto, 19L);
//        Teacher teacher = teacherRepository.findByFirstName("Federico");
//        assertEquals("Federico", teacher.getFirstName());
//    }
//
//    @AfterEach
//    public void setupAfterTransaction(){
//        jdbc.execute("DELETE FROM teacher");
//    }
}
