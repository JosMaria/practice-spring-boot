package com.genesiscode.practicespringboot.service;

import com.genesiscode.practicespringboot.domain.Student;
import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import com.genesiscode.practicespringboot.problems.exceptions.EmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface StudentService {

    List<StudentResponseDto> getStudents();

    StudentResponseDto addNewStudent(StudentCreateDto studentCreateDto);

    void deleteStudent(Long id);

    StudentResponseDto updateStudent(Long id, String name, String email);

    Collection<StudentResponseDto> findByDobBetween(LocalDate start, LocalDate end);
}
