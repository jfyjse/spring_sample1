package com.jfy.sample1.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
   @Autowired

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student){

        Optional<Student> studentByEmail=studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent())
        {
            throw new IllegalStateException("email already in use");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("student with id "+studentId+ " not found");
        }
        studentRepository.deleteById(studentId);

    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student=studentRepository.findById(studentId).orElseThrow( ()->
                new IllegalStateException("student with id "+studentId+" not found"));

        if(name!=null && name.length()>0 &&
        !Objects.equals(student.getName(),name)){
            student.setName(name);

        }
//        else { throw new IllegalStateException("name is same no need to update"); }
//        throw new IllegalStateException("name is same");


        if (email!=null && email.length()>0 &&
        !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional =studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email in use");
            }
            student.setEmail(email);
        }

    }
}
