package com.example.attendance.service.impl;

import com.example.attendance.entity.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import com.example.attendance.service.AttendanceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAttendanceByCourse(String courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }

    @Override
    public List<Attendance> getStudentAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public long countAbsentStudents(String courseId) {
        return attendanceRepository.countByCourseIdAndStatus(courseId, "ABSENT");
    }
}
