package com.example.attendance.service;

import com.example.attendance.entity.User;
import java.util.List;

public interface UserService {
    // 新增教师用户
    int addUser(User user);

    // 根据ID查询
    User getUserById(Long id);

    // 根据用户名查询（登录）
    User getUserByUsername(String username);

    // 查询所有教师
    List<User> getAllTeachers();

    // 更新用户
    int updateUser(User user);

    // 删除用户
    int deleteUser(Long id);

    User register(User user);

    User register(String username, String password, String role);
}