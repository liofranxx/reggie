package com.lyc.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/10 16:29
 */
@Data
public class Employee implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber; // 身份证
    private Integer status;


    @TableField(value = "create_time", fill = FieldFill.INSERT) // 插入时填充字段
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE) // 插入和更新时填充字段
    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)  // 插入时填充字段
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE) // 插入和更新时填充字段
    private Long updateUser;
}
