package com.jfy.sample1.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student test1= new Student(
                    "test1name",
                    "test1@gmail.com",
                    LocalDate.of(1990, Month.APRIL,1)
            );
            Student test2= new Student(
                    "test2name",
                    "test2@gmail.com",
                    LocalDate.of(2005, Month.APRIL,9)
            );

            repository.saveAll(
                    List.of(test1,test2)
            );

        };
    }
}
