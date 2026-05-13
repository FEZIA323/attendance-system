package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 🔥 重点：把 @RestController 改成 @Controller
// 这样既能返回页面，也能返回接口（加 @ResponseBody）
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ======================== 你原来的接口【100% 保留不动】========================
    // 查询某课程的所有选课学生
    @GetMapping("/course/{courseId}")
    @ResponseBody  // 加这个就能返回JSON
    public Result<List<Student>> getStudentsByCourse(@PathVariable String courseId) {
        return Result.success(studentService.getStudentsByCourse(courseId));
    }

    // 查询某学生的所有选课记录
    @GetMapping("/{studentId}/courses")
    @ResponseBody
    public Result<List<Student>> getStudentCourses(@PathVariable String studentId) {
        return Result.success(studentService.getStudentCourses(studentId));
    }

    // 统计某课程的选课人数
    @GetMapping("/course/{courseId}/count")
    @ResponseBody
    public Result<Long> countCourseStudents(@PathVariable String courseId) {
        return Result.success(studentService.countCourseStudents(courseId));
    }

    // ======================== 本周作业：页面接口（新增）========================

    // 1. 跳转到 新增选课页面
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // 2. 保存选课（新增/修改）
    @PostMapping("/save")
    public String save(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students/list";
    }

    // 3. 跳转到 编辑页面（数据回显）
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student-form";
    }

    // 4. 选课列表 + 分页
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model
    ) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Student> studentPage = studentService.findAll(pageable);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        return "student-list";
    }

    // 5. 删除选课记录
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }
}