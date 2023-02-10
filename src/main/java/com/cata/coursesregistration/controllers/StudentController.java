package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
import com.cata.coursesregistration.services.StudentService;
import com.cata.coursesregistration.wrappers.ClassesWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public Student saveStudent(@RequestBody StudentDto student) {
        return studentService.saveNew(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody StudentDto student, @PathVariable Long id) {
        return studentService.update(student, id);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return "Student " + id + " successfully deleted";
    }

    @PostMapping("/{id}/select")
    public Student selectClasses(@PathVariable Long id, @RequestBody ClassesWrapper classes) {
        return studentService.addClasses(id, classes.getClasses());
    }
}
