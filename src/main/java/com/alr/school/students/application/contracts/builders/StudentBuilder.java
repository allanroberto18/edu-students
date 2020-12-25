package com.alr.school.students.application.contracts.builders;

import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;

public interface StudentBuilder {
    Student buildStudent(StudentDTO studentDTO);
}
