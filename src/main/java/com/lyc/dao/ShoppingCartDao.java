package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/21 16:44
 */
@Mapper
public interface ShoppingCartDao extends BaseMapper<ShoppingCart> {
}
