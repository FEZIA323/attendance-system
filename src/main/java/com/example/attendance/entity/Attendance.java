package com.example.attendance.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attendance") // 对应你的考勤表
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", nullable = false, length = 20)
    private String studentId;

    @Column(name = "student_name", nullable = false, length = 50)
    private String studentName;

    @Column(name = "course_id", nullable = false, length = 20)
    private String courseId;

    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime; // 签到时间

    @Column(name = "seat_row")
    private Integer seatRow; // 座位行号

    @Column(name = "seat_col")
    private Integer seatCol; // 座位列号

    @Column(name = "status", nullable = false, length = 20)
    private String status = "NORMAL"; // 考勤状态：NORMAL/LATE/ABSENT

    @Column(name = "ip", length = 15)
    private String ip; // 签到IP

    @Column(name = "create_time", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime createTime;
}