package com.genesiscode.practicespringboot.service.impl;

import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import com.genesiscode.practicespringboot.repository.StudentRepository;
import com.genesiscode.practicespringboot.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImpl(studentRepository, modelMapper);
    }

    @Test
    void getStudents() {
    }

    @Test
    void getAllStudentsPaginated() {
        //GIVEN
        int numberPage = 1;
        int sizePage = 5;

        //WHEN
        List<StudentResponseDto> studentsPaginated = underTest.getAllStudentsPaginated(numberPage, sizePage);

        //THEN
        verify(studentRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void addNewStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void findByDobBetween() {
    }
}