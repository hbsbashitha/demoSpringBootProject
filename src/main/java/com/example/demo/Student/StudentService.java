package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {


    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email is taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudentById(int studentId) {
        if(!studentRepository.existsById(Integer.valueOf(studentId))){
            throw new IllegalStateException("not student with given id");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentDetails(int studentId, String name, String email) {
        Student student = studentRepository.findStudentById(studentId).
                orElseThrow(()->new IllegalStateException("can not find with given user id") );
        student.setName(name);
        student.setEmail(email);
        
    }
}
