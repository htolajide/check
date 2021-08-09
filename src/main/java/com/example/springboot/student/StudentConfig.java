package com.example.springboot.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student mariam = new Student("Mariam Hammed", "mariamhammed@yahoo.com", LocalDate.of(2000, Month.APRIL, 5));
		    Student hakeem = new Student("Hakeem Adepoju", "hakeemadepoju@yahoo.com", LocalDate.of(1990, Month.JANUARY, 5));
            studentRepository.saveAll(List.of(mariam, hakeem));
        };
    }
    
}
