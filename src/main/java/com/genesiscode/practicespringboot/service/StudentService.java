package com.genesiscode.practicespringboot.service;

import com.genesiscode.practicespringboot.domain.Student;
import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import com.genesiscode.practicespringboot.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDto> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> studentMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }

    public StudentResponseDto addNewStudent(StudentCreateDto studentCreateDto) {
        //Verify if email exists
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentCreateDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        // Mapping the student to be persisted.
        Student studentToPersist = studentMapper.map(studentCreateDto, Student.class);

        // Saving the student
        Student persistedStudent = studentRepository.save(studentToPersist);

        //Mapping student to DTO
        return studentMapper.map(persistedStudent, StudentResponseDto.class);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (! exists) {
            throw new IllegalStateException("Student with ID: " + id + " does not exists.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public StudentResponseDto updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with ID " + id + " does not exists"));

        if (name != null && name.length() > 0 && ! Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && ! Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
        return studentMapper.map(student, StudentResponseDto.class);
    }
}
