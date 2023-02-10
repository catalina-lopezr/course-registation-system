package com.cata.coursesregistration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Builder
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
