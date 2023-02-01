package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.dtos.GradeDto;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<GradeDto> getAll();

    GradeDto getById(Long id);

    Grade saveNew(GradeDto grade);

    Grade update(GradeDto newGrade, Long id);

    void delete(Long id);
}
