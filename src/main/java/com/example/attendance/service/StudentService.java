package com.example.attendance.service;

import com.example.attendance.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentService {
    List<Student> getStudentsByCourse(String courseId);
    List<Student> getStudentCourses(String studentId);
    long countCourseStudents(String courseId);

    void save(Student student);          // 保存/新增
    Student findById(Integer id);        // 根据ID查询
    Page<Student> findAll(Pageable pageable); // 分页查询
    void deleteById(Integer id);        // 删除
}
