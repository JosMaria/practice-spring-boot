package com.genesiscode.practicespringboot.student;

import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/student")
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
    public ResponseEntity<StudentResponseDto> registerNewStudent(@RequestBody StudentCreateDto studentCreateDto) {
        StudentResponseDto studentResponseDto = studentService.addNewStudent(studentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }
}
