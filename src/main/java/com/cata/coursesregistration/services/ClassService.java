package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.ClassDto;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<ClassDto> getAll();

    ClassDto getById(Long id);

    Class saveNew(ClassDto course);

    Class update(ClassDto cl, Long id);

    void delete(Long id);
}
