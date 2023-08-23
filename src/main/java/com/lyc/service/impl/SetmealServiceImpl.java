package com.lyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.common.CustomException;
import com.lyc.dao.SetmealDao;
import com.lyc.domain.Setmeal;
import com.lyc.domain.SetmealDish;
import com.lyc.dto.SetmealDto;
import com.lyc.service.SetmealDishService;
import com.lyc.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 16:59
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealDao, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;


    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        // 保存套餐基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealDto.getId());
        });
        // 保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    @Transactional
    @Override
    public void removeWithDish(List<Long> ids) {
        // 查询套餐状态，确实是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(queryWrapper);
        // 如果不能删除抛出一个业务异常
        if (count > 0){
            throw new CustomException("套餐售卖中，不能删除！");
        }
        // 如果可以删除,先删除套餐表中的数据
        this.removeByIds(ids);
        // 再删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        // delete from setmeal_dish where setmeal_id in (1,2,3)
        wrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(wrapper);
    }
}
