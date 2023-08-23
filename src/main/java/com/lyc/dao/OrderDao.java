package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.domain.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/23 9:47
 */
@Mapper
public interface OrderDao extends BaseMapper<Orders> {
}
