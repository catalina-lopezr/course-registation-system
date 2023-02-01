package com.cata.coursesregistration.mappers;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper( TeacherMapper.class );
    Teacher toBo(TeacherDto teacher);
    TeacherDto toDto(Teacher teacher);
}
