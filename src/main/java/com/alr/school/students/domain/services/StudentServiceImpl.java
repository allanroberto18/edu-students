package com.alr.school.students.domain.services;

import org.springframework.stereotype.Component;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import com.alr.school.students.domain.contracts.services.StudentService;
import com.alr.school.students.infrastructure.repositories.StudentRepository;
import com.alr.school.students.application.contracts.builders.StudentBuilder;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Student> update(Integer id, StudentDTO studentDTO) {
        Optional<Student> student = this.studentRepository.findById(id);
        student.ifPresent(opt -> {
            opt.setFirstName(studentDTO.getFirstName());
            opt.setLastName(studentDTO.getLastName());
            opt.setEmail(studentDTO.getEmail());

            studentRepository.saveAndFlush(opt);
        });

        return student;
    }

    @Override
    public Optional<Student> getStudentById(Integer id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public Optional<Student> deleteStudent(Integer id) {
        Optional<Student> student = this.studentRepository.findById(id);
        student.ifPresent(studentRepository::delete);

        return student;
    }
}
