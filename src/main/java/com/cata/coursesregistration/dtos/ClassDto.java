package com.cata.coursesregistration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ClassDto {

    private String code;

    private LocalDate startDate;

    private LocalDate finishDate;

    private LocalTime startTime;

    private LocalTime finishTime;

    private Long teacherID;

    private Long courseID;
}
