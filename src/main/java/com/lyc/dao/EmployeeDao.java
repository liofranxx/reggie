package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/10 16:37
 */
@Mapper
public interface EmployeeDao extends BaseMapper<com.lyc.domain.Employee> {
}
