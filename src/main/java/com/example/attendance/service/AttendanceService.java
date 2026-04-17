package com.example.attendance.service;

import com.example.attendance.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    List<Attendance> getAttendanceByCourse(String courseId);
    List<Attendance> getStudentAttendance(String studentId);
    long countAbsentStudents(String courseId);
}
