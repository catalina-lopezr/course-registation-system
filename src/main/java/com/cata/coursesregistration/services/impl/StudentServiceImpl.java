package com.cata.coursesregistration.services.impl;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
import com.cata.coursesregistration.mappers.StudentMapper;
import com.cata.coursesregistration.repositories.StudentRepository;
import com.cata.coursesregistration.services.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(Student student : students){
            studentDtos.add(studentMapper.toDto(student));
        }
        return studentDtos;
    }

    @Override
    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow();
        return studentMapper.toDto(student);
    }

    @Override
    public Student saveNew(StudentDto student) {
        Student newStudent = studentMapper.toBo(student);
        newStudent.setCreatedBy("cata");
        newStudent.setCreatedDate(LocalDateTime.now());
        return studentRepository.save(newStudent);
    }

    @Override
    public Student update(StudentDto student, Long id) {
        return studentRepository.findById(id)
                .map(updatedStudent -> {
                    updatedStudent.setFirstName(student.getFirstName());
                    updatedStudent.setLastName(student.getLastName());
                    updatedStudent.setSemester(student.getSemester());
                    updatedStudent.setUpdatedBy("cata");
                    updatedStudent.setLastUpdated(LocalDateTime.now());
                    return studentRepository.save(updatedStudent);
                })
                .orElseGet(() -> {
                    Student newStudent = studentMapper.toBo(student);
                    newStudent.setCreatedBy("cata");
                    newStudent.setCreatedDate(LocalDateTime.now());
                    return studentRepository.save(newStudent);
                });
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
