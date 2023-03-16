/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;

@Service 
public class StudentH2Service implements StudentRepository{
    @Autowired 
    private JdbcTemplate db;
    @Override
    public ArrayList<Student> getStudents(){
        List<Student> studentList = db.query("select * from student", new StudentRowMapper());

        ArrayList<Student> students = new ArrayList<>(studentList);
        return students;
    }
    @Override
    public Student getStudentById(int studentId){
        try{
            Student student = db.queryForObject("select * from student where studentId=? ",new StudentRowMapper(), studentId);
            return student;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override

    public Student addStudent(Student student){
        db.update("insert into student(studentName, gender, standard) values(?,?,?)", student.getStudentName(), student.getGender(), student.getStandard());
        Student savedStudent = db.queryForObject("select * from student where studentName=? and gender=? and standard=?", new StudentRowMapper(),student.getStudentName(), student.getGender(), student.getStandard());
        return savedStudent;
    } 
    
    @Override 

    public String addMultipleStudents(ArrayList<Student> studentList){
         for (Student eachStudent : studentList) {
            db.update("insert into student(studentName,gender,standard) values (?,?,?)", eachStudent.getStudentName(),eachStudent.getGender(), eachStudent.getStandard());
        }
        String responseMessage = String.format("Successfully added %d students", studentList.size()); 
        return responseMessage;
    }

    @Override 

    public Student updateStudent(int studentId, Student student){
        if(student.getStudentName() != null){
            db.update("Update student SET studentName=? WHERE studentId=?", student.getStudentName(), studentId);
        }
        if(student.getGender() != null){
            db.update("Update student SET gender=? WHERE studentId=?", student.getGender(), studentId);
        }
        if(String.valueOf(student.getStandard()) != null){
            db.update("Update student SET standard=? WHERE studentId=?", student.getStandard(), studentId);
        }
        return getStudentById(studentId);
    }
    @Override 
    public void deleteStudent(int studentId){
        db.update("Delete from student where studentId=? ", studentId);
    }
}
