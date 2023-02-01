package com.cata.coursesregistration.bootstrap;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.repositories.ClassRepository;
import com.cata.coursesregistration.repositories.CourseRepository;
import com.cata.coursesregistration.repositories.StudentRepository;
import com.cata.coursesregistration.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class BootStrapData implements CommandLineRunner {
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    public BootStrapData(ClassRepository classRepository, CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Course math = new Course("Mathematics 1", "MT2604", 3, 1 );
//        courseRepository.save(math);
//
//        Teacher sara = new Teacher("Sara", "Londono");
//        teacherRepository.save(sara);
//
//        Class mathClass = new Class("2604-1", LocalDate.parse("2023-01-11"),
//                LocalDate.parse("2023-05-11"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"),
//                sara, math);
//        classRepository.save(mathClass);
//
//        Student cata = new Student("Catalina", "Lopez", 8);
//        studentRepository.save(cata);
    }
}
