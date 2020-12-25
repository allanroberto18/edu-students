package com.alr.school.students.domain.contracts.services;

import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;

public interface StudentService {

    Student save(StudentDTO studentDTO);
}
