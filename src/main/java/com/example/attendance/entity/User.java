package com.example.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;                // 对应id BIGINT
    private String username;        // 对应username
    private String password;        // 对应password
    private String realName;        // 对应real_name
    private String role;            // 对应role
    private LocalDateTime createTime;// 对应create_time
}