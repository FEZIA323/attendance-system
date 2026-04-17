package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 查询某课程的所有选课学生
    @GetMapping("/course/{courseId}")
    public Result<List<Student>> getStudentsByCourse(@PathVariable String courseId) {
        return Result.success(studentService.getStudentsByCourse(courseId));
    }

    // 查询某学生的所有选课记录
    @GetMapping("/{studentId}/courses")
    public Result<List<Student>> getStudentCourses(@PathVariable String studentId) {
        return Result.success(studentService.getStudentCourses(studentId));
    }

    // 统计某课程的选课人数
    @GetMapping("/course/{courseId}/count")
    public Result<Long> countCourseStudents(@PathVariable String courseId) {
        return Result.success(studentService.countCourseStudents(courseId));
    }
}
