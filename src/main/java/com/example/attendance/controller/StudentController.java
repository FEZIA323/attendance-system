package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.Student;
import com.example.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    // 构造函数注入
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 任务一：路径参数查询学生信息
    @GetMapping("/student/info/{studentId}")
    public Result<Student> getStudentById(@PathVariable String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            return Result.success(student);
        }
        return Result.error("未找到该学生");
    }

    // 任务二：查询参数查询学生列表（含简单分页）
    @GetMapping("/student/list")
    public Result<List<Student>> getStudentList(
            @RequestParam String className,
            @RequestParam(defaultValue = "1") int page) {
        List<Student> list = studentService.getStudentList(className);
        // 简单分页处理
        int pageSize = 2;
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, list.size());
        List<Student> pageList = list.subList(fromIndex, toIndex);
        return Result.success(pageList);
    }
}