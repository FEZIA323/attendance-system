package com.example.attendance.dao;

import com.example.attendance.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAllTeachers() {
        try {
            String sql = "SELECT * FROM userlist WHERE role='TEACHER'";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            return List.of();
        }
    }

    public User findById(Long id) {
        try {
            String sql = "SELECT * FROM userlist WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (Exception e) {
            return null;
        }
    }

    public int insert(User user) {
        String sql = "INSERT INTO userlist(username,password,real_name,role) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getRealName(),
                user.getRole()
        );
    }

    public int update(User user) {
        String sql = "UPDATE userlist SET username=?,password=?,real_name=?,role=? WHERE id=?";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getRealName(),
                user.getRole(),
                user.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM userlist WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM userlist WHERE username=?";
            List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}