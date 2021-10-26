package com.genesiscode.practicespringboot.configuration;

import com.genesiscode.practicespringboot.dto.StudentCreateDto;
import com.genesiscode.practicespringboot.domain.Student;
import com.genesiscode.practicespringboot.dto.StudentResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean("studentMapper")
    public ModelMapper studentMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        //Configuring mapping when Student to StudentResponseDto
        modelMapper.addMappings(new PropertyMap<Student, StudentResponseDto>() {
            @Override
            protected void configure() {
                //map().setAge(source.getAge());
                //skip(destination.getEmail());
            }
        });

        //Configuring mapping when StudentCreateDto to Student
        modelMapper.addMappings(new PropertyMap<StudentCreateDto, Student>() {
            @Override
            protected void configure() {
                map().setDob(source.getBirth());
            }
        });

        return modelMapper;
    }
}
