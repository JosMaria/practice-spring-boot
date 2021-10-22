package com.genesiscode.practicespringboot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student jose_maria = new Student("Jose Maria", "josmariaguilar@gmail.com",
                    LocalDate.of(1998, JANUARY, 17));
            Student darleen = new Student("Darleen", "daleen@gmail.com",
                    LocalDate.of(2003, AUGUST, 1));
            studentRepository.saveAll(List.of(jose_maria, darleen));
        };
    }
}
