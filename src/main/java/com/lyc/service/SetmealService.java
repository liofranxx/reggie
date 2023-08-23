package com.lyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.domain.Setmeal;
import com.lyc.dto.SetmealDto;

import java.util.List;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 16:58
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);
    public void removeWithDish(List<Long> ids);
}
