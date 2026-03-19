package com.example.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    // 响应状态码（200成功/500失败）
    private Integer code;
    // 响应提示信息
    private String msg;
    // 响应数据
    private T data;

    // 成功响应封装方法
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败响应封装方法
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }
}