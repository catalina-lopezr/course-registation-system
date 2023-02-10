package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Teacher;
import com.cata.coursesregistration.dtos.TeacherDto;
import com.cata.coursesregistration.mappers.TeacherMapper;
import com.cata.coursesregistration.repositories.TeacherRepository;
import com.cata.coursesregistration.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TeacherMVCTest {

    private static MockHttpServletRequest request;
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherMapper teacherMapper;

    private static List<TeacherDto> teacherDtos;

    @BeforeAll
    public static void teachersSetup() {
        TeacherDto teacher1 = new TeacherDto("Daniel", "Correa");
        TeacherDto teacher2 = new TeacherDto("Carlos", "Montoya");
        TeacherDto teacher3 = new TeacherDto("Edwin", "Montoya");
        teacherDtos = new ArrayList<>(Arrays.asList(teacher1, teacher2, teacher3));
    }

    @Test
    public void getAllTeachers_success() throws Exception {
        when(teacherService.getAll()).thenReturn(teacherDtos);
        assertIterableEquals(teacherDtos, teacherService.getAll());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/teachers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();
    }

    @Test
    public void getById_success() throws Exception {
        when(teacherService.getById(1L)).thenReturn(teacherDtos.get(1));
        assertEquals(teacherDtos.get(1), teacherService.getById(1L));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Carlos")))
                .andReturn();
    }

    @Test
    public void createTeacher_success() throws Exception {
        Teacher teacher = teacherMapper.toBo(teacherDtos.get(2));

        when(teacherService.saveNew(teacherDtos.get(2))).thenReturn(teacher);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(teacher));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Edwin")));
    }

    @Test
    public void updateTeacher_success() throws Exception {
//        TeacherDto updatedTeacher = new TeacherDto("Edwin", "Cardona");
        TeacherDto updatedTeacher = TeacherDto.builder().firstName("Edwin").lastName("Cardona").build();
        when(teacherService.getById(1L)).thenReturn(teacherDtos.get(1));
        when(teacherService.update(updatedTeacher, 1L)).thenReturn(teacherMapper.toBo(updatedTeacher));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/teachers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedTeacher));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.lastName", is("Cardona")));
    }

    @Test
    public void deleteTeacherById_success() throws Exception {
        when(teacherService.getById(1L)).thenReturn(teacherDtos.get(1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
