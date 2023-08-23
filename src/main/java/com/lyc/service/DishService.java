package com.lyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.domain.Dish;
import com.lyc.dto.DishDto;

import java.util.ArrayList;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 16:55
 */
public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);
    // 根据id查询菜品信息和口味信息
    public DishDto getByIdWhitFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
    public void deleteWithFlavor(ArrayList<Long> ids);
}
