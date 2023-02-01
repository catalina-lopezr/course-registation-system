package com.cata.coursesregistration.mappers;

import com.cata.coursesregistration.domain.Course;
import com.cata.coursesregistration.dtos.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class );
    Course toBo(CourseDto course);
    CourseDto toDto(Course course);

}
