package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // 查询某课程的所有考勤记录
    @GetMapping("/course/{courseId}")
    public Result<List<Attendance>> getAttendanceByCourse(@PathVariable String courseId) {
        return Result.success(attendanceService.getAttendanceByCourse(courseId));
    }

    // 查询某学生的所有考勤记录
    @GetMapping("/{studentId}/records")
    public Result<List<Attendance>> getStudentAttendance(@PathVariable String studentId) {
        return Result.success(attendanceService.getStudentAttendance(studentId));
    }

    // 统计某课程的缺勤人数
    @GetMapping("/course/{courseId}/absent-count")
    public Result<Long> countAbsentStudents(@PathVariable String courseId) {
        return Result.success(attendanceService.countAbsentStudents(courseId));
    }
}