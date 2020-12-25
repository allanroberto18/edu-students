package com.alr.school.students.infrastructure.controllers;

import com.alr.school.students.domain.contracts.services.StudentService;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(path = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> create(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = this.studentService.save(studentDTO);

        return new ResponseEntity(
            student,
            HttpStatus.CREATED
        );
    }
}
