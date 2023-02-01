package com.cata.coursesregistration.services;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;

import java.util.List;

public interface TeacherService {
    public List<TeacherDto> getAll();
    public TeacherDto getById(Long id);
    public Teacher saveNew(TeacherDto teacher);
    public Teacher update(TeacherDto teacher, Long id);
    public String delete(Long id);
}
