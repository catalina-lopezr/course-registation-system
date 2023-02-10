package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.ClassDto;
import com.cata.coursesregistration.services.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<ClassDto> getAllClasses() {
        return classService.getAll();
    }

    @GetMapping("/{id}")
    public ClassDto getClass(@PathVariable Long id) {
        return classService.getById(id);
    }

    @PostMapping
    public Class saveClass(@RequestBody ClassDto cl) {
        return classService.saveNew(cl);
    }

    @PutMapping("/{id}")
    public Class updateClass(@RequestBody ClassDto newClass, @PathVariable Long id) {
        return classService.update(newClass, id);
    }

    @DeleteMapping("/{id}")
    String deleteClass(@PathVariable Long id) {
        return classService.delete(id);
    }
}
