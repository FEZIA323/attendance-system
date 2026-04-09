package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/userlist")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/teachers")
    public Result<List<User>> getAllTeachers() {
        return Result.success(userService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("添加成功");
    }

    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success("删除成功");
    }

    @GetMapping("/login")
    public Result<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) return Result.error("用户名不存在");
        if (!user.getPassword().equals(password)) return Result.error("密码错误");
        return Result.success(user);
    }
}