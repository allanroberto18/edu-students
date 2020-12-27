package com.alr.school.students.domain.services;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.application.contracts.builders.StudentBuilder;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.repositories.StudentRepository;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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

    @Test
    public void testUpdateStudent_WithIdStudentDTO_MustUpdateAndReturnStudentObject() {
        Integer id = 1;
        StudentDTO dto = DataHelperForTest.buildStudentDTO("User", "DtoUpdate", "user_dto@test.com");
        Student studentExpected = DataHelperForTest.buildStudent("User", "DtoUpdate", "user_dto@test.com");
        Student studentMockedToGet = DataHelperForTest.buildStudent(id, "User", "Dto", "user_dto@test.com");

        when(this.studentRepository.findById(id)).thenReturn(Optional.of(studentMockedToGet));
        when(this.studentRepository.saveAndFlush(studentExpected)).thenReturn(studentExpected);

        Optional<Student> student = this.studentService.update(id, dto);

        Assertions.assertEquals(studentExpected.getLastName(), student.get().getLastName());
    }

    @Test
    public void testUpdateStudent_WithId_MustReturnEmptyObject() {
        Integer id = 1;
        StudentDTO dto = DataHelperForTest.buildStudentDTO("User", "DtoUpdate", "user_dto@test.com");

        when(this.studentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Student> student = this.studentService.update(id, dto);

        Assertions.assertEquals(Optional.empty(), student);
    }

    @Test
    public void testGetStudents_NoArguments_MustReturnEmptyObject() {
        when(this.studentRepository.findAll()).thenReturn(List.of());

        List<Student> students = this.studentService.getStudents();

        Assertions.assertTrue(students.size() == 0);
    }

    @Test
    public void testGetStudentById_WithId_MustReturnEmptyObject() {
        Integer id = 1;
        when(this.studentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Student> student = this.studentService.getStudentById(id);

        Assertions.assertEquals(Optional.empty(), student);
    }

    @Test
    public void testGetStudentById_WithId_MustStudentObject() {
        Integer id = 1;
        Student studentMocked = DataHelperForTest.buildStudent("User", "Dto", "user_dto@test.com");
        when(this.studentRepository.findById(id)).thenReturn(Optional.of(studentMocked));

        Optional<Student> student = this.studentService.getStudentById(id);

        Assertions.assertEquals(studentMocked, student.get());
    }

    @Test
    public void testDeleteStudent_WithIdStudentDTO_MustStudentObject() {
        Integer id = 2;
        Student studentMocked = DataHelperForTest.buildStudent("User", "Dto", "user_dto@test.com");
        when(this.studentRepository.findById(id)).thenReturn(Optional.of(studentMocked));

        Optional<Student> student = this.studentService.deleteStudent(id);

        Assertions.assertEquals(studentMocked, student.get());
    }

    @Test
    public void testDeleteStudent_WithId_MustReturnEmptyObject() {
        Integer id = 2;
        when(this.studentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Student> student = this.studentService.deleteStudent(id);

        Assertions.assertEquals(Optional.empty(), student);
    }
}
