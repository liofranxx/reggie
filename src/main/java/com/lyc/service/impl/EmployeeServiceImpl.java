package com.lyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.dao.EmployeeDao;
import com.lyc.domain.Employee;
import com.lyc.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/10 16:38
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee>
        implements EmployeeService {
}
