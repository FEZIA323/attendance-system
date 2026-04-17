package com.example.attendance.service;

import com.example.attendance.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getStudentsByCourse(String courseId);
    List<Student> getStudentCourses(String studentId);
    long countCourseStudents(String courseId);
}
