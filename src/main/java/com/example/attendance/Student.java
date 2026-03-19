package com.example.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    // 补充Lombok注解（自动生成get/set/构造方法）
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Student {
        private String studentId; // 学号
        private String name;      // 姓名
        private String className; // 班级（补充字段）
        private Integer age;      // 年龄（补充字段）
    }

