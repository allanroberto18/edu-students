package com.alr.school.students.application.builders;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentBuilderUnitTest {

    private StudentBuilderImpl studentBuilder;

    @BeforeEach
    public void init() {
        this.studentBuilder = new StudentBuilderImpl();
    }

    @Test
    public void buildStudent_WithStudentDTO_MustReturnStudentObj() {
        StudentDTO dto = DataHelperForTest.buildStudentDTO("User", "Dto", "user_dto@test.com");
        Student student = this.studentBuilder.buildStudent(dto);

        Assertions.assertEquals(dto.getFirstName(), student.getFirstName());
        Assertions.assertEquals(dto.getLastName(), student.getLastName());
        Assertions.assertEquals(dto.getEmail(), student.getEmail());
    }
}
