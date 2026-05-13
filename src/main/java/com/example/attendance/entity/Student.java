package com.example.attendance.entity;

import lombok.Data;
import jakarta.persistence.*;  // 这里改成 jakarta，不是 javax
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "course_selection")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false, length = 20)
    private String studentId;

    @Column(name = "student_name", nullable = false, length = 50)
    private String studentName;

    @Column(name = "gender", length = 2)
    private String gender;

    @Column(name = "course_id", nullable = false, length = 20)
    private String courseId;

    @Column(name = "select_time")
    private LocalDateTime selectTime;
}