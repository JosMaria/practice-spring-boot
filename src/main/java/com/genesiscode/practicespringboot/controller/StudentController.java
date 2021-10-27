package com.genesiscode.practicespringboot.controller;

import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import com.genesiscode.practicespringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Collection<StudentResponseDto>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> registerNewStudent(@Valid @RequestBody StudentCreateDto studentCreateDto) {
        StudentResponseDto studentResponseDto = studentService.addNewStudent(studentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable("id") Long id,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String email) {
        StudentResponseDto studentResponseDto = studentService.updateStudent(id, name, email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentResponseDto);
    }

    @GetMapping(path = "/date")
    public ResponseEntity<Collection<StudentResponseDto>> findByDobBetween(@PastOrPresent @RequestParam String startDate,
                                                                           @PastOrPresent @RequestParam String endDate) {
        Collection<StudentResponseDto> studentsResponse =
                studentService.findByDobBetween(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(studentsResponse);
    }
}
