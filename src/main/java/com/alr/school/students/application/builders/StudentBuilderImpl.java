package com.alr.school.students.application.builders;

import com.alr.school.students.application.contracts.builders.StudentBuilder;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentBuilderImpl implements StudentBuilder {

    @Override
    public Student buildStudent(StudentDTO studentDTO) {
        return Student
            .builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
            .build();
    }
}
