package com.example.attendance.controller;

import com.example.attendance.Result;
import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userlist")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // ========== 页面跳转 ==========
    @GetMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("username"));
        return "index";
    }

    // ========== 注册提交 ==========
    @PostMapping("/registerPage")
    public String registerPage(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String role,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMsg", "两次密码不一致");
            return "register";
        }

        try {
            userService.register(username, password, role);
            return "redirect:/userlist/loginPage";
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "register";
        }
    }

    // ========== 登录提交 ==========
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            model.addAttribute("errorMsg", "用户不存在");
            return "login";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("errorMsg", "密码错误");
            return "login";
        }

        session.setAttribute("username", username);
        return "redirect:/userlist/index";
    }

    // ========== 退出 ==========
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/userlist/loginPage";
    }

    // ========== 原有接口 ==========
    @GetMapping("/teachers")
    @ResponseBody
    public Result<List<User>> getAllTeachers() {
        return Result.success(userService.getAllTeachers());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user == null ? Result.error("用户不存在") : Result.success(user);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("添加成功");
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success("删除成功");
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerApi(@RequestBody Map<String, String> req) {
        User user = userService.register(
                req.get("username"),
                req.get("password"),
                req.get("role")
        );
        return ResponseEntity.ok("注册成功：" + user.getUsername());
    }
}