package com.cata.coursesregistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CourseDto {
    private String name;
    private String code;
    private int credits;
    private int semester;
}
