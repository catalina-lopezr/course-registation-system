package com.cata.coursesregistration.dtos;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.domain.Student;
import lombok.Data;

@Data
public class GradeDto {
    private float value;
    private Long studentID;

    private Long clID;
    private String yearSemester;
}
