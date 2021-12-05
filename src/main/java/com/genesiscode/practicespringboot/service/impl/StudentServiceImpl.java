package com.genesiscode.practicespringboot.service.impl;

import com.genesiscode.practicespringboot.domain.Student;
import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import com.genesiscode.practicespringboot.problems.exceptions.EmailAlreadyExistsException;
import com.genesiscode.practicespringboot.repository.StudentRepository;
import com.genesiscode.practicespringboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper studentMapper;

    @Override
    public List<StudentResponseDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> studentMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDto> getAllStudentsPaginated(int numberPage, int sizePage) {
        Pageable of = PageRequest.of(numberPage, sizePage);
        Page<Student> pageStudent = studentRepository.findAll(of);
        return pageStudent.stream()
                .map(student -> studentMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto addNewStudent(StudentCreateDto studentCreateDto) {
        //Verify if email exists
        Optional<Student> studentOptional = studentRepository.findByEmail(studentCreateDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new EmailAlreadyExistsException("email taken");
        }

        // Mapping the student to be persisted.
        Student studentToPersist = studentMapper.map(studentCreateDto, Student.class);

        // Saving the student
        Student persistedStudent = studentRepository.save(studentToPersist);

        //Mapping student to DTO
        return studentMapper.map(persistedStudent, StudentResponseDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (! exists) {
            throw new IllegalStateException("Student with ID: " + id + " does not exists.");
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with ID " + id + " does not exists"));

        if (name != null && name.length() > 0 && ! Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && ! Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
        return studentMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public Collection<StudentResponseDto> findByDobBetween(LocalDate start, LocalDate end) {
        return studentRepository.findByDobBetween(start, end)
                .stream()
                .map(student -> studentMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }
}
