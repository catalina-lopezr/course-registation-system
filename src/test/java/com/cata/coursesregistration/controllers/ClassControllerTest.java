package com.cata.coursesregistration.controllers;

import com.cata.coursesregistration.domain.Class;
import com.cata.coursesregistration.dtos.ClassDto;
import com.cata.coursesregistration.services.ClassService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@TestPropertySource("/application.properties")
@SpringBootTest
public class ClassControllerTest {

    @MockBean
    private ClassService classService;

    @Autowired
    private ClassController classController;

    private static List<ClassDto> classDtos;

    @BeforeAll
    public static void classesSetup() {
        ClassDto class1 = ClassDto.builder()
                .code("ST345")
                .build();
        ClassDto class2 =  ClassDto.builder()
                .code("ST346")
                .build();
        ClassDto class3 =  ClassDto.builder()
                .code("ST347")
                .build();
        classDtos = new ArrayList<>(Arrays.asList(class1, class2, class3));
    }

    @DisplayName("Get all Classes")
    @Test
    void assertGetAllClasses() {
        when(classService.getAll()).thenReturn(classDtos);
        assertIterableEquals(classDtos, classController.getAllClasses());
        assertEquals(3, classController.getAllClasses().size());
        assertEquals("ST347",
                classController.getAllClasses().get(2).getCode());
    }

    @DisplayName("Get Class By Id")
    @Test
    void assertGetClassById() {
        when(classService.getById(0L)).thenReturn(classDtos.get(0));
        assertEquals(classDtos.get(0), classController.getClass(0L));
        assertEquals("ST345",
                classController.getClass(0L).getCode());
    }

    @DisplayName("Save Class")
    @Test
    void assertSaveClass() {
        Class cl = Class.builder().code("ST346").build();
        when(classService.saveNew(classDtos.get(1))).thenReturn(cl);
        assertEquals(cl, classController.saveClass(classDtos.get(1)));
    }

    @DisplayName("Update Class")
    @Test
    void assetUpdateClass() {
        Class cl = Class.builder().code("ST346").build();;
        when(classService.update(classDtos.get(1), 1L)).thenReturn(cl);
        assertEquals(cl, classController.updateClass(classDtos.get(1), 1L));
    }

    @DisplayName("Delete Class")
    @Test
    void assertDeleteClass() {
        when(classService.delete(1L)).thenReturn("Class 1 successfully deleted");
        assertEquals("Class 1 successfully deleted", classController.deleteClass(1L));
    }

}
