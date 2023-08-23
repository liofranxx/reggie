package com.lyc.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.domain.Category;
import com.lyc.domain.Dish;

import java.util.List;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 15:56
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
