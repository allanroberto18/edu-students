package com.alr.school.students.domain.services;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.application.contracts.builders.StudentBuilder;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.repositories.StudentRepository;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentServiceUnitTest {

    private StudentBuilder studentBuilder;
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;

    @BeforeEach
    public void init() {
        this.studentBuilder = mock(StudentBuilder.class);
        this.studentRepository = mock(StudentRepository.class);

        this.studentService = new StudentServiceImpl(
          this.studentBuilder,
          this.studentRepository
        );
    }

    @Test
    public void testAddNewStudent_WithStudentDTO_MustReturnStudentObject() {
        StudentDTO dto = DataHelperForTest.buildStudentDTO("User", "Dto", "user_dto@test.com");
        Student studentExpected = DataHelperForTest.buildStudent(dto);

        when(this.studentBuilder.buildStudent(dto)).thenReturn(studentExpected);
        when(this.studentRepository.save(studentExpected)).thenReturn(studentExpected);

        Student student = this.studentService.save(dto);

        Assertions.assertEquals(studentExpected.getFirstName(), student.getFirstName());
        Assertions.assertEquals(studentExpected.getLastName(), student.getLastName());
        Assertions.assertEquals(studentExpected.getEmail(), student.getEmail());
    }
}
