package com.lyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.dao.UserDao;
import com.lyc.domain.User;
import com.lyc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/16 19:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
