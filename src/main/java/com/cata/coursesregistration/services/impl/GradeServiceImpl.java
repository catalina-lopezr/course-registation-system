package com.cata.coursesregistration.services.impl;

import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.GradeDto;
import com.cata.coursesregistration.mappers.GradeMapper;
import com.cata.coursesregistration.repositories.ClassRepository;
import com.cata.coursesregistration.repositories.GradeRepository;
import com.cata.coursesregistration.repositories.StudentRepository;
import com.cata.coursesregistration.services.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper, StudentRepository studentRepository, ClassRepository classRepository) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    @Override
    public List<GradeDto> getAll() {
        List<Grade> gardes = gradeRepository.findAll();
        List<GradeDto> gradeDtos = new ArrayList<>();
        for (Grade grade : gardes){
            GradeDto gradeDto = gradeMapper.toDto(grade);
            Class gradeClass = grade.getCl();
            Student student = grade.getStudent();
            gradeDto.setClID(gradeClass.getId());
            gradeDto.setStudentID(student.getId());
            gradeDtos.add(gradeDto);
        }
        return gradeDtos;
    }

    @Override
    public GradeDto getById(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow();
        GradeDto gradeDto = gradeMapper.toDto(grade);
        Class gradeClass = grade.getCl();
        Student student = grade.getStudent();
        gradeDto.setClID(gradeClass.getId());
        gradeDto.setStudentID(student.getId());
        return gradeDto;
    }

    @Override
    public Grade saveNew(GradeDto grade) {
        Grade newGrade = gradeMapper.toBo(grade);
        Student newStudent = studentRepository.findById(grade.getStudentID()).orElseThrow();
        Class newClass = classRepository.findById(grade.getClID()).orElseThrow();
        newGrade.setStudent(newStudent);
        newGrade.setCl(newClass);
        newGrade.setCreatedBy("cata");
        newGrade.setCreatedDate(LocalDateTime.now());
        return gradeRepository.save(newGrade);
    }

    @Override
    public Grade update(GradeDto grade, Long id) {
        return gradeRepository.findById(id)
                .map(updatedGrade -> {
                    updatedGrade.setValue(grade.getValue());
                    updatedGrade.setCl(classRepository.findById(grade.getClID()).orElseThrow());
                    updatedGrade.setStudent(studentRepository.findById(grade.getStudentID()).orElseThrow());
                    updatedGrade.setYearSemester(grade.getYearSemester());
                    updatedGrade.setUpdatedBy("cata");
                    updatedGrade.setLastUpdated(LocalDateTime.now());
                    return gradeRepository.save(updatedGrade);
                })
                .orElseGet(() -> {
                    Grade newGrade = gradeMapper.toBo(grade);
                    Student newStudent = studentRepository.findById(grade.getStudentID()).orElseThrow();
                    Class newClass = classRepository.findById(grade.getClID()).orElseThrow();
                    newGrade.setStudent(newStudent);
                    newGrade.setCl(newClass);
                    newGrade.setCreatedBy("cata");
                    newGrade.setCreatedDate(LocalDateTime.now());
                    return gradeRepository.save(newGrade);
                });
    }

    @Override
    public void delete(Long id) {
        gradeRepository.deleteById(id);
    }
}
