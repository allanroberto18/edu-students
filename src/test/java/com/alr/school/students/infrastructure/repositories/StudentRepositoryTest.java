package com.alr.school.students.infrastructure.repositories;

import com.alr.school.students.DataHelperForTest;
import com.alr.school.students.domain.entities.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void save_withStudentObject_MustSaveOnDatabase() {
        Student studentExpected = DataHelperForTest.buildStudent("some", "student", "some_student@test.com");
        Student student = studentRepository.save(studentExpected);

        Assertions.assertNotNull(student.getId());
        Assertions.assertEquals(studentExpected.getFirstName(), student.getFirstName());
        Assertions.assertEquals(studentExpected.getLastName(), student.getLastName());
        Assertions.assertEquals(studentExpected.getEmail(), student.getEmail());
    }
}
