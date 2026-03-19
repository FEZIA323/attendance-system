package com.example.attendance.controller;

import com.example.attendance.Attendance;
import com.example.attendance.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 考勤控制器（任务三专用）
 */
@RestController
public class AttendanceController {

    // 任务三：JSON请求体更新考勤记录
    @PostMapping("/attendance/update")
    public Result<String> updateAttendance(@RequestBody Attendance attendance) {
        // 模拟业务逻辑：打印接收的考勤数据
        System.out.println("更新考勤记录：" + attendance);
        return Result.success("考勤记录更新成功");
    }
}


