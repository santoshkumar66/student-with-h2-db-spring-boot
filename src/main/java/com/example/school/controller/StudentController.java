/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.school.service.StudentH2Service;

import com.example.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;

@RestController 
public class StudentController {
    @Autowired
    public StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents(){
        return studentService.getStudents();
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PostMapping("/students/bulk")

    public String addMultipleStudents(@RequestBody ArrayList<Student> studentList) {

        return studentService.addMultipleStudents(studentList);
    }

    @PutMapping("/students/{studentId}")

    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student){
        return studentService.updateStudent(studentId, student);
    }
    @DeleteMapping("/students/{studentId}")

    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentService.deleteStudent(studentId);
    }
}
