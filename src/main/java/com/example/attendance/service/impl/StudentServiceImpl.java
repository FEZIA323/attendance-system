package com.example.attendance.service.impl;

import com.example.attendance.entity.Student;
import com.example.attendance.repository.StudentRepository;
import com.example.attendance.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // ================== 你原来的方法（不动）==================
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

    // ================== 本周作业新增方法 ==================
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}