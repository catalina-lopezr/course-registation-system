package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import com.cata.coursesregistration.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public Teacher saveTeacher(@RequestBody TeacherDto teacher) {
        return teacherService.saveNew(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@RequestBody TeacherDto newTeacher, @PathVariable Long id) {
        return teacherService.update(newTeacher, id);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return "Teacher " + id + " successfully deleted";
    }
}
