package com.cata.coursesregistration.mappers;

import com.cata.coursesregistration.domain.Student;
import com.cata.coursesregistration.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper( StudentMapper.class );
    Student toBo(StudentDto student);
    StudentDto toDto(Student student);
}
