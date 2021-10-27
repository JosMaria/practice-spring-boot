package com.genesiscode.practicespringboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class StudentCreateDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email
    private String email;
    private LocalDate birth;

    public StudentCreateDto() {}

    public StudentCreateDto(String name, String email, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
