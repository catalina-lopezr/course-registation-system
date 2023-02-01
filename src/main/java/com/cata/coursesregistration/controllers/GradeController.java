package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.services.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<GradeDto> getAllGrades() {
        return gradeService.getAll();
    }

    @GetMapping("/{id}")
    public GradeDto getGrade(@PathVariable Long id) {
        return gradeService.getById(id);
    }

    @PostMapping
    public Grade saveGrade(@RequestBody GradeDto teacher) {
        return gradeService.saveNew(teacher);
    }

    @PutMapping("/{id}")
    public Grade replaceGrade(@RequestBody GradeDto newGrade, @PathVariable Long id) {
        return gradeService.update(newGrade, id);
    }

    @DeleteMapping("/{id}")
    void deleteGrade(@PathVariable Long id) {
        gradeService.delete(id);
    }
}
