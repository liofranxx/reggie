package com.lyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyc.domain.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 15:55
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
