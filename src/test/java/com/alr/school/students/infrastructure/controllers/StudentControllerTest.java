package com.alr.school.students.infrastructure.controllers;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void create_WithStudentDTO_MustReturnStatus201() throws Exception {
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/student")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(2)
    public void create_WithStudentDTONoFirstName_MustReturnStatus400() throws Exception {
        StudentDTO student = DataHelperForTest.buildStudentDTO("", "student", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/student")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(3)
    public void create_WithStudentDTONoLastName_MustReturnStatus400() throws Exception {
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/student")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(4)
    public void create_WithStudentDTONoEmail_MustReturnStatus400() throws Exception {
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "");
        String body = DataHelperForTest.convertToJson(student);
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/student")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(5)
    public void create_WithStudentDTOInvalidEmail_MustReturnStatus400() throws Exception {
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "some_student=test");
        String body = DataHelperForTest.convertToJson(student);
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.post("/student")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(6)
    public void update_WithIdAndStudentDTO_MustReturnStatus202() throws Exception {
        Integer id = 1;
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isAccepted())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(7)
    public void update_WithEmptyIdAndStudentDTO_MustReturnStatus400() throws Exception {
        Integer id = null;
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(8)
    public void update_WithIdAndStudentDTONoFirstName_MustReturnStatus400() throws Exception {
        Integer id = 1;
        StudentDTO student = DataHelperForTest.buildStudentDTO("", "student", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(9)
    public void update_WithIdAndStudentDTONoLastName_MustReturnStatus400() throws Exception {
        Integer id = 1;
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "", "some_student@test.com");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(10)
    public void update_WithIdAndStudentDTONoEmail_MustReturnStatus400() throws Exception {
        Integer id = 1;
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(11)
    public void update_WithIdAndStudentDTOInvalidEmail_MustReturnStatus400() throws Exception {
        Integer id = 1;
        StudentDTO student = DataHelperForTest.buildStudentDTO("some", "student", "some_student=test");
        String body = DataHelperForTest.convertToJson(student);

        this.mockMvc
            .perform(
                MockMvcRequestBuilders.put("/student?id=" + id)
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(12)
    public void getAll_WithNoArguments_MustReturnStatus200() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/student/all")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(13)
    public void getStudent_WithId_MustReturnStatus200() throws Exception {
        Integer id = 1;
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/student?id=" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(14)
    public void getStudent_WithId_MustReturnStatus204() throws Exception {
        Integer id = 999;
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/student?id=" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(15)
    public void getStudent_WithNoId_MustReturnStatus400() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/student?id=" + null)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(16)
    public void removeStudent_WithId_MustReturnStatus202() throws Exception {
        Integer id = 1;
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.delete("/student?id=" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isAccepted())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @Order(17)
    public void removeStudent_WithId_MustReturnStatus204() throws Exception {
        Integer id = 999;
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.delete("/student?id=" + id)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @Order(18)
    public void removeStudent_WithNoId_MustReturnStatus400() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.delete("/student?id=" + null)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
