package com.cata.coursesregistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StudentDto {

    private String firstName;
    private String lastName;
    private int semester;

}
