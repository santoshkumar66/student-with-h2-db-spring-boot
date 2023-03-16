// Write your code here
package com.example.school.repository;

import java.util.*;
import com.example.school.model.Student;

public interface StudentRepository{
    ArrayList<Student> getStudents();
    Student getStudentById(int studentId);
    String addMultipleStudents(ArrayList<Student> studentList);
    Student addStudent(Student student);
    Student updateStudent(int studentId, Student student);
    void deleteStudent(int studentId);
}
