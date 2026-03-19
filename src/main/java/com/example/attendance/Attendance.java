package com.example.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    /**
     * 考勤记录实体类（任务三专用）
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Attendance {
        private String studentId; // 学生学号
        private String date;       // 考勤日期
        private String status;     // 考勤状态（正常/迟到/缺勤）
    }

