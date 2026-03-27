package com.example.attendance.dao;

import com.example.attendance.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {

    // 静态数据源（和你原来一致）
    private static final List<Student> studentList = new ArrayList<>();

    static {
        studentList.add(new Student("42311142", "魏韵卓", "计算机2", 20));
        studentList.add(new Student("2023002", "李四", "计算机3", 19));
        studentList.add(new Student("2023003", "王五", "计算机2", 20));
    }

    // 根据学号查询
    public Student findById(String studentId) {
        for (Student student : studentList) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // 根据班级查询
    public List<Student> findByClassName(String className) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getClassName().equals(className)) {
                result.add(student);
            }
        }
        return result;
    }
}