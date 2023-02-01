package com.cata.coursesregistration.mappers;

import com.cata.coursesregistration.domain.Grade;
import com.cata.coursesregistration.dtos.GradeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper( GradeMapper.class );
    Grade toBo(GradeDto grade);
    GradeDto toDto (Grade grade);
}
