package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
import com.cata.coursesregistration.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Student replaceStudent(@RequestBody StudentDto student, @PathVariable Long id) {
        return studentService.update(student, id);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }
}
