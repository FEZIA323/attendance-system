package com.example.attendance.service.impl;

import com.example.attendance.entity.Student;
import com.example.attendance.repository.StudentRepository;
import com.example.attendance.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentsByCourse(String courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    @Override
    public List<Student> getStudentCourses(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public long countCourseStudents(String courseId) {
        return studentRepository.countByCourseId(courseId);
    }
}