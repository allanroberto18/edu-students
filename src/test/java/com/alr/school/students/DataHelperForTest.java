package com.alr.school.students;

import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataHelperForTest {

    public static StudentDTO buildStudentDTO(String firstName, String lastName, String email) {
        return StudentDTO
            .builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .build();
    }

    public static Student buildStudent(StudentDTO studentDTO) {
        return Student
            .builder()
            .firstName(studentDTO.getFirstName())
            .lastName(studentDTO.getLastName())
            .email(studentDTO.getEmail())
            .build();
    }

    public static Student buildStudent(String firstName, String lastName, String email) {
        return Student
            .builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .build();
    }

    public static Student buildStudent(Integer id, String firstName, String lastName, String email) {
        Student student = buildStudent(firstName, lastName, email);
        student.setId(id);

        return student;
    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper Obj = new ObjectMapper();
        return Obj.writeValueAsString(object);
    }
}
