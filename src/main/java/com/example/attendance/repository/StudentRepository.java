package com.example.attendance.repository;

import com.example.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 1. 根据学号查询学生选课记录（自动实现SQL）
    List<Student> findByStudentId(String studentId);

    // 2. 根据课程ID查询所有选课学生
    List<Student> findByCourseId(String courseId);

    // 3. 根据学生姓名模糊查询（包含匹配）
    List<Student> findByStudentNameContaining(String keyword);

    // 4. 统计某课程的选课人数
    long countByCourseId(String courseId);

    // 补充到 StudentRepository
    List<Student> findByCourseIdAndStudentNameContaining(String courseId, String keyword);
}