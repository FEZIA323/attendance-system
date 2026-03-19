package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // 1. 模拟学生数据源（无需修改）
    private static final List<Student> studentList = new ArrayList<>();
    static {
        // 初始化测试数据
        studentList.add(new Student("42311142", "魏韵卓", "计算机2", 20));
        studentList.add(new Student("2023002", "李四", "计算机3", 19));
        studentList.add(new Student("2023003", "王五", "计算机2", 20));
    }

    // 2. 任务一：路径参数查询学生信息
    @GetMapping("/student/info/{studentId}")
    public Result<Student> getStudentById(@PathVariable String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return Result.success(student);
            }
        }
        return Result.error("未找到该学生");
    }

    // 3. 任务二：查询参数查询学生列表
    @GetMapping("/student/list")
    public Result<List<Student>> getStudentList(
            @RequestParam String className,  // 班级（必传）
            @RequestParam(defaultValue = "1") int page) { // 页码（默认1）
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getClassName().equals(className)) {
                result.add(student);
            }
        }
        return Result.success(result);
    }
}
