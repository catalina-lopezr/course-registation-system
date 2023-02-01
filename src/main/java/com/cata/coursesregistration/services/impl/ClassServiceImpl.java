package com.cata.coursesregistration.services.impl;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.ClassDto;
import com.cata.coursesregistration.mappers.ClassMapper;
import com.cata.coursesregistration.repositories.ClassRepository;
import com.cata.coursesregistration.repositories.CourseRepository;
import com.cata.coursesregistration.repositories.TeacherRepository;
import com.cata.coursesregistration.services.ClassService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public ClassServiceImpl(ClassRepository classRepository, ClassMapper classMapper,
                            TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.classRepository = classRepository;
        this.classMapper = classMapper;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<ClassDto> getAll() {
        List<Class> classes = classRepository.findAll();
        List<ClassDto> classDtos = new ArrayList<>();
        for (Class cl : classes){
            ClassDto classDto = classMapper.toDto(cl);
            Teacher teacher = cl.getTeacher();
            Course course = cl.getCourse();
            classDto.setTeacherID(teacher.getId());
            classDto.setCourseID(course.getId());
            classDtos.add(classDto);
        }
        return classDtos;
    }

    @Override
    public ClassDto getById(Long id) {
        Class cl = classRepository.findById(id).orElseThrow();
        ClassDto classDto = classMapper.toDto(cl);
        Teacher teacher = cl.getTeacher();
        Course course = cl.getCourse();
        classDto.setTeacherID(teacher.getId());
        classDto.setCourseID(course.getId());
        return classDto;
    }

    @Override
    public Class saveNew(ClassDto cl) {
        Class newClass = classMapper.toBo(cl);
        Course newCourse =  courseRepository.findById(cl.getCourseID()).orElseThrow();
        Teacher newTeacher = teacherRepository.findById(cl.getTeacherID()).orElseThrow();
        newClass.setTeacher(newTeacher);
        newClass.setCourse(newCourse);
        newClass.setCreatedBy("cata");
        newClass.setCreatedDate(LocalDateTime.now());
        return classRepository.save(newClass);
    }

    @Override
    public Class update(ClassDto cl, Long id) {
        return classRepository.findById(id)
                .map(newClass -> {
                    newClass.setCode(cl.getCode());
                    newClass.setStartDate(cl.getStartDate());
                    newClass.setFinishDate(cl.getFinishDate());
                    newClass.setStartTime(cl.getFinishTime());
                    newClass.setFinishTime(cl.getFinishTime());
                    newClass.setCourse(courseRepository.findById(cl.getCourseID()).orElseThrow());
                    newClass.setTeacher(teacherRepository.findById(cl.getTeacherID()).orElseThrow());
                    return classRepository.save(newClass);
                })
                .orElseGet(() -> {
                    Class newClass = classMapper.toBo(cl);
                    Course newCourse =  courseRepository.findById(cl.getCourseID()).orElseThrow();
                    Teacher newTeacher = teacherRepository.findById(cl.getTeacherID()).orElseThrow();
                    newClass.setTeacher(newTeacher);
                    newClass.setCourse(newCourse);
                    newClass.setUpdatedBy("cata");
                    newClass.setLastUpdated(LocalDateTime.now());
                    return classRepository.save(newClass);
                });
    }

    @Override
    public void delete(Long id) {
        classRepository.deleteById(id);
    }
}
