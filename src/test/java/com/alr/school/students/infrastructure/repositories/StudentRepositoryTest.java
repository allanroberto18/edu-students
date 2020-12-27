package com.alr.school.students.infrastructure.repositories;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.domain.entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void init() {
        List<Student> students = new ArrayList<>();
        students.add(DataHelperForTest.buildStudent("some1", "student", "some1_student@test.com"));
        students.add(DataHelperForTest.buildStudent("some2", "student", "some2_student@test.com"));
        students.add(DataHelperForTest.buildStudent("some3", "student", "some3_student@test.com"));

        studentRepository.saveAll(students);
    }

    @Test
    public void save_withStudentObject_MustSaveOnDatabase() {
        Student studentExpected = DataHelperForTest.buildStudent("some", "student", "some_student@test.com");
        Student student = studentRepository.save(studentExpected);

        Assertions.assertNotNull(student.getId());
        Assertions.assertEquals(studentExpected.getFirstName(), student.getFirstName());
        Assertions.assertEquals(studentExpected.getLastName(), student.getLastName());
        Assertions.assertEquals(studentExpected.getEmail(), student.getEmail());
    }

    @Test
    public void findAll_WithNoArguments_MustReturnListOfStudents() {
        List<Student> students = studentRepository.findAll();

        Assertions.assertTrue(students.size() > 0);
    }

    @Test
    public void findById_WithStudentId_MustReturnStudentObject() {
        Optional<Student> student = studentRepository.findById(1);

        Assertions.assertTrue(student.isPresent());
    }

    @Test
    public void findById_WithStudentIdThatDoesntExist_MustReturnOptionalEmpty() {
        Optional<Student> student = studentRepository.findById(999);

        Assertions.assertTrue(student.isEmpty());
    }

    @Test
    public void save_withStudentObject_MustUpdateOnDatabase() {
        Optional<Student> optionalStudent = studentRepository.findById(1);

        String firstName = "SomeNew";

        optionalStudent.ifPresent(obj -> {
            obj.setFirstName(firstName);
            studentRepository.saveAndFlush(obj);
        });

        Optional<Student> optionalStudentExpected = studentRepository.findById(1);

        Assertions.assertEquals(firstName, optionalStudentExpected.get().getFirstName());
    }

    @Test
    public void delete_WithId_MustRemoveStudent() {
        Optional<Student> studentToBeExcluded = studentRepository.findById(2);
        studentRepository.delete(studentToBeExcluded.get());

        Optional<Student> studentExcluded = studentRepository.findById(2);

        Assertions.assertTrue(studentExcluded.isEmpty());
    }
}
