package com.example.attendance.service.impl;

import com.example.attendance.dao.StudentDao;
import com.example.attendance.Student;
import com.example.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudentById(String studentId) {
        return studentDao.findById(studentId);
    }

    @Override
    public List<Student> getStudentList(String className) {
        return studentDao.findByClassName(className);
    }
}