package com.cata.coursesregistration.mappers;

import com.cata.coursesregistration.dtos.ClassDto;
import com.cata.coursesregistration.domain.Class;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    ClassMapper INSTANCE = Mappers.getMapper( ClassMapper.class );
    Class toBo(ClassDto cl);
    ClassDto toDto(Class cl);

}
