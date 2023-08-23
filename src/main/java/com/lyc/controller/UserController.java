package com.lyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyc.common.Result;
import com.lyc.domain.User;
import com.lyc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/16 19:43
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@RequestBody User user, HttpServletRequest request){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, user.getPhone());
        User one = userService.getOne(wrapper);
        if(one == null){
            // 新用户，自动完成注册
            user.setStatus(1);
            user.setName("用户" + user.getPhone());
            userService.save(user);
        }
        request.getSession().setAttribute("user", user.getId());
        return Result.success(user);
    }
}
