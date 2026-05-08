package com.example.attendance.service.impl;

import com.example.attendance.dao.UserDao;
import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int addUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        return userDao.insert(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getAllTeachers() {
        return userDao.findAllTeachers();
    }

    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userDao.deleteById(id);
    }

    // ✅ 正确的注册方法
    @Override
    public User register(User user) {
        User exist = userDao.findByUsername(user.getUsername());
        if (exist != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("STUDENT");
        }
        user.setCreateTime(LocalDateTime.now());

        userDao.insert(user);
        return user;
    }

    // ✅ 不用的方法直接实现空
    @Override
    public User register(String username, String password, String role) {
        return null;
    }
}