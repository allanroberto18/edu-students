package com.alr.school.students.domain.contracts.services;

import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student save(StudentDTO studentDTO);
    List<Student> getStudents();
    Optional<Student> update(Integer id, StudentDTO studentDTO);
    Optional<Student> getStudentById(Integer id);
    Optional<Student> deleteStudent(Integer id);
}
