package com.alr.school.students.domain.services;

import org.springframework.stereotype.Component;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import com.alr.school.students.domain.contracts.services.StudentService;
import com.alr.school.students.infrastructure.repositories.StudentRepository;
import com.alr.school.students.application.contracts.builders.StudentBuilder;

@Component
public class StudentServiceImpl implements StudentService {

    private StudentBuilder studentBuilder;
    private StudentRepository studentRepository;

    public StudentServiceImpl(
        StudentBuilder studentBuilder,
        StudentRepository studentRepository
    ) {
        this.studentBuilder = studentBuilder;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(StudentDTO studentDTO) {
        Student student = this.studentBuilder.buildStudent(studentDTO);
        return this.studentRepository.save(student);
    }
}
