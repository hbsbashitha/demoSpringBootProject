package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> allStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable int studentId){
        studentService.deleteStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public void updateStudentDetails(@PathVariable int studentId,
                                     @PathParam("name") String name,
                                     @PathParam("email") String email){

        studentService.updateStudentDetails(studentId,name,email);
    }


}
