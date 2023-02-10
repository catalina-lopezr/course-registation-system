package com.cata.coursesregistration.dtos;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GradeDto {
    private float value;
    private Long studentID;

    private Long clID;
    private String yearSemester;
}
