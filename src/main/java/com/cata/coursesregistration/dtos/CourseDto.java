package com.cata.coursesregistration.dtos;

import lombok.Data;

@Data
public class CourseDto {
    private String name;
    private String code;
    private int credits;
    private int semester;
}
