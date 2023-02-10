package com.cata.coursesregistration.services.impl;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import com.cata.coursesregistration.mappers.TeacherMapper;
import com.cata.coursesregistration.repositories.TeacherRepository;
import com.cata.coursesregistration.services.TeacherService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper, EntityManager entityManager) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<TeacherDto> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for(Teacher teacher : teachers){
            teacherDtos.add(teacherMapper.toDto(teacher));
        }
        return teacherDtos;
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();
        return teacherMapper.toDto(teacher);
    }

    @Override
    public Teacher saveNew(TeacherDto teacher){
        Teacher newTeacher = teacherMapper.toBo(teacher);
        newTeacher.setCreatedBy("cata");
        newTeacher.setCreatedDate(LocalDateTime.now());
        return teacherRepository.save(newTeacher);
    }

    @Override
    public Teacher update(TeacherDto teacher, Long id) {
        return teacherRepository.findById(id)
                .map(updatedTeacher -> {
                    updatedTeacher.setFirstName(teacher.getFirstName());
                    updatedTeacher.setLastName(teacher.getLastName());
                    updatedTeacher.setUpdatedBy("cata");
                    updatedTeacher.setLastUpdated(LocalDateTime.now());
                    return teacherRepository.save(updatedTeacher);
                })
                .orElseGet(() -> { //Bad practice
                    Teacher newTeacher = teacherMapper.toBo(teacher);
                    newTeacher.setCreatedBy("cata");
                    newTeacher.setCreatedDate(LocalDateTime.now());
                    return teacherRepository.save(newTeacher);
                });
    }

    @Override
    public String delete(Long id) {
        teacherRepository.findById(id).orElseThrow();
        teacherRepository.deleteById(id);
        return "Teacher " + id + " successfully deleted";
    }
}
