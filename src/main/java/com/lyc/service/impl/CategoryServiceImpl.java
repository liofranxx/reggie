package com.lyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.common.CustomException;
import com.lyc.dao.CategoryDao;
import com.lyc.domain.Category;
import com.lyc.domain.Dish;
import com.lyc.domain.Setmeal;
import com.lyc.service.CategoryService;
import com.lyc.service.DishService;
import com.lyc.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 15:57
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService{
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        // 查询当前分类是否关联相关的菜品, 如果已经关联则抛出异常
        dishWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishWrapper);
        if(count1 > 0){
            throw new CustomException("当前分类关联了菜品，不能删除");
        }
        // 查询当前分类是否关联相关的套餐, 如果已经关联则抛出异常
        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        setmealWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealWrapper);
        if(count2 > 0){
            throw new CustomException("当前分类关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
