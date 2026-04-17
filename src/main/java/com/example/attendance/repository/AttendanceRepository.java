package com.example.attendance.repository;

import com.example.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    // 1. 根据课程ID查询所有考勤记录
    List<Attendance> findByCourseId(String courseId);

    // 2. 根据学号查询该学生的所有考勤记录
    List<Attendance> findByStudentId(String studentId);

    // 3. 根据课程ID和状态查询考勤记录（如查询某课程的缺勤学生）
    List<Attendance> findByCourseIdAndStatus(String courseId, String status);


    // 4. 统计某课程的缺勤人数
    long countByCourseIdAndStatus(String courseId, String status);
}
