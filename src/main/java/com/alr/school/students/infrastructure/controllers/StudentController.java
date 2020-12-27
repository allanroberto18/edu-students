package com.alr.school.students.infrastructure.controllers;

import com.alr.school.students.domain.contracts.services.StudentService;
import com.alr.school.students.domain.entities.Student;
import com.alr.school.students.infrastructure.request.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

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

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> update(
        @Valid @NotBlank @RequestParam(name = "id") Integer id,
        @Valid @RequestBody StudentDTO studentDTO
    ) {
        Optional<Student> student = this.studentService.update(id, studentDTO);
        if (student.isEmpty()) {
            return new ResponseEntity(
                null,
                HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity(
            student.get(),
            HttpStatus.ACCEPTED
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> getStudent(
        @Valid @NotBlank @RequestParam(name = "id") Integer id
    ) {
        Optional<Student> student = this.studentService.getStudentById(id);
        if (student.isEmpty()) {
            return new ResponseEntity(
                null,
                HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity(
            student.get(),
            HttpStatus.OK
        );
    }

    @GetMapping(
        path = "/all",
        produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = this.studentService.getStudents();

        return new ResponseEntity(
            students,
            HttpStatus.OK
        );
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Student> deleteStudent(
        @Valid @NotBlank @RequestParam(name = "id") Integer id
    ) {
        Optional<Student> student = this.studentService.deleteStudent(id);
        if (student.isEmpty()) {
            return new ResponseEntity(
                null,
                HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity(
            student.get(),
            HttpStatus.ACCEPTED
        );
    }
}
