package com.alr.school.students.infrastructure.controllers;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.junit.jupiter.api.Test;
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
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
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

}
