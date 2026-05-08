package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.Attendance;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


import java.time.LocalDateTime;
import java.util.Random;

// 项目启动自动执行

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceService attendanceService,
                                AttendanceRepository attendanceRepositoryDao) {
        this.attendanceService = attendanceService;
        this.attendanceRepository = attendanceRepositoryDao;
    }

    // 查询某课程的所有考勤记录
    @GetMapping("/course/{courseId}")
    public Result<List<Attendance>> getAttendanceByCourse(@PathVariable String courseId) {
        return Result.success(attendanceService.getAttendanceByCourse(courseId));
    }

    // 查询某学生的所有考勤记录
    @GetMapping("/{studentId}/records")
    public Result<List<Attendance>> getStudentAttendance(@PathVariable String studentId) {
        return Result.success(attendanceService.getStudentAttendance(studentId));
    }

    // 统计某课程的缺勤人数
    @GetMapping("/course/{courseId}/absent-count")
    public Result<Long> countAbsentStudents(@PathVariable String courseId) {
        return Result.success(attendanceService.countAbsentStudents(courseId));
    }

    @GetMapping("/create/test")
    public Result<String> createTestData() {
        Random rd = new Random();
        String[] status = {"NORMAL", "LATE", "ABSENT"};

        // 循环生成 100 条测试数据
        for (int i = 1; i <= 100; i++) {
            Attendance att = new Attendance();
            att.setStudentId("2023" + String.format("%04d", i));
            att.setStudentName("学生" + i);
            att.setCourseId("CS001");
            // 随机过去60天内的签到时间
            att.setCheckInTime(LocalDateTime.now().minusDays(rd.nextInt(60)));
            // 随机考勤状态
            att.setStatus(status[rd.nextInt(3)]);
            attendanceRepository.save(att);
        }
        return Result.success("成功循环生成100条考勤测试数据，可用于分页、排序、多条件查询测试");
    }

    @GetMapping("/page")
    public Result<Page<Attendance>> getAttendancePage(
            // 默认第0页，每页10条
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        // 构建分页对象
        Pageable pageable = PageRequest.of(page, size);
        // 执行分页查询
        Page<Attendance> pageResult = attendanceRepository.findAll(pageable);
        return Result.success(pageResult);
    }

    @GetMapping("/page/sort")
    public Result<Page<Attendance>> getAttendancePageWithSort(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            // 排序字段：默认按签到时间 checkInTime
            @RequestParam(defaultValue = "checkInTime") String sortField,
            // 排序方向：默认降序 desc
            @RequestParam(defaultValue = "desc") String sortDirection
    ) {
        // 1. 构建排序对象
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);

        // 2. 构建带排序的分页对象
        Pageable pageable = PageRequest.of(page, size, sort);

        // 3. 查询并返回
        Page<Attendance> pageResult = attendanceRepository.findAll(pageable);
        return Result.success(pageResult);
    }

    // ====================== 【任务3：Specification多条件动态分页查询】 ======================
    @GetMapping("/search")
    public Result<Page<Attendance>> attendanceDynamicSearch(
            // 分页参数
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            // 排序参数
            @RequestParam(defaultValue = "checkInTime") String sortField,
            @RequestParam(defaultValue = "desc") String sortDirection,
            // 动态查询条件（全部非必填）
            @RequestParam(required = false) String studentId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime
    ) {
        // 1. 构建分页+排序对象
        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        // 2. 动态条件拼接（完全适配jakarta新包，类型完全匹配）
        Specification<Attendance> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 学号精确匹配
            if (studentId != null && !studentId.isEmpty()) {
                predicates.add(cb.equal(root.get("studentId"), studentId));
            }
            // 考勤状态匹配
            if (status != null && !status.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            // 签到时间起始范围
            if (startTime != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("checkInTime"), startTime));
            }
            // 签到时间结束范围
            if (endTime != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("checkInTime"), endTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        // 3. 执行综合查询（条件+分页+排序）
        Page<Attendance> result = attendanceRepository.findAll(spec, pageable);
        return Result.success(result);
    }
}
    // ==============================================================

