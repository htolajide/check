package com.example.springboot.student;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents(){
        return studentRepository.findAll();
        // return List.of(
		// 	new Student("Mariam Hammed", "mariamhammed@yahoo.com", LocalDate.of(2000, Month.JANUARY, 5), 21),
		// 	new Student("Hakeem Adepoju", "hakeemadepoju@yahoo.com", LocalDate.of(1990, Month.JANUARY, 5), 30)
		// );
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                                            .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }


    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException(
                "Student with Id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new IllegalStateException(
                "Student with Id " + studentId + " does not exist"
            ));
        if(name != null && name.length() > 0 
            && !Objects.equals(student.getName(), name) ){
                student.setName(name);
        }
        if(email != null && email.length() > 0 
            && !Objects.equals(student.getEmail(), email) ){
                Optional<Student> optionalStudent = 
                    studentRepository.findStudentByEmail(email);
                if(optionalStudent.isPresent()){
                    throw new IllegalStateException("Email taken");
                }
                student.setEmail(email);
        }
        
    }
        
    
}
