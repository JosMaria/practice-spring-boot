package com.genesiscode.practicespringboot.service;

import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface StudentService {

    List<StudentResponseDto> getStudents();

    List<StudentResponseDto> getAllStudentsPaginated(int numberPage, int sizePage);

    StudentResponseDto addNewStudent(StudentCreateDto studentCreateDto);

    void deleteStudent(Long id);

    StudentResponseDto updateStudent(Long id, String name, String email);

    Collection<StudentResponseDto> findByDobBetween(LocalDate start, LocalDate end);
}
