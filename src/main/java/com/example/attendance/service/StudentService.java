package com.example.attendance.service;

import com.example.attendance.Student;
import java.util.List;

public interface StudentService {
    Student getStudentById(String studentId);
    List<Student> getStudentList(String className);
}