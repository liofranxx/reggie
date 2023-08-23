package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/23 9:46
 */
@Mapper
public interface OrderDetailDao extends BaseMapper<OrderDetail> {
}
