package com.lyc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.common.CustomException;
import com.lyc.dao.DishDao;
import com.lyc.domain.Dish;
import com.lyc.domain.DishFlavor;
import com.lyc.domain.SetmealDish;
import com.lyc.dto.DishDto;
import com.lyc.service.DishFlavorService;
import com.lyc.service.DishService;
import com.lyc.service.SetmealDishService;
import com.lyc.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 16:56
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishDao, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增菜品同时保存对应的口味数据
     * @param dishDto
     */
    @Transactional
    public void saveWithFlavor(DishDto dishDto){
        // 保存基本信息到菜品表dish
        this.save(dishDto);
        Long id = dishDto.getId();
        //保存口味数据到菜品口味表dishFlavor
        List<DishFlavor> flavors = dishDto.getFlavors();
        /*stream流方法*/
//        flavors.stream().map((item) ->{
//            item.setDishId(id);
//            return item;
//        }).collect(Collectors.toList());
        /*forEach方法加匿名函数*/
        flavors.forEach(f -> f.setDishId(id));
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public DishDto getByIdWhitFlavor(Long id) {
        Dish dish = this.getById(id);
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorService.list(wrapper);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        // 保存基本信息到菜品表dish
        this.updateById(dishDto);
        Long id = dishDto.getId();
        //清理当前菜品对应的口味数据
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId, id);
        dishFlavorService.remove(wrapper);
        //保存口味数据到菜品口味表dishFlavor
        List<DishFlavor> flavors = dishDto.getFlavors();
        /*forEach方法加匿名函数*/
        flavors.forEach(f -> f.setDishId(id));
        dishFlavorService.saveBatch(flavors);
    }

    @Override
    public void deleteWithFlavor(ArrayList<Long> ids) {
        // 首先判断菜品是否停售，没有停售不允许删除
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Dish::getId, ids);
        wrapper.eq(Dish::getStatus, 1);
        int count = this.count(wrapper);
        if(count > 0){
            throw new CustomException("菜品售卖中，不能删除！");
        }
        // TODO：停售后的菜品对应的套餐也应该停售，那么停售后菜品删除，套餐应该删除吗？
        // 再判断菜品是否在售卖中的套餐内，有则不允许删除
//        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.in(SetmealDish::getDishId, ids);
//        int countInMeal = setmealDishService.count(lambdaQueryWrapper);
//        if(countInMeal > 0){
//            throw new CustomException("有包含菜品的套餐在售卖中，不能删除！");
//        }
        // 首先删除对应菜品在菜品表Dish中的记录
        this.removeByIds(ids);
        // 然后删除对应菜品在口味表DishFlavor中的记录
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(DishFlavor::getDishId, ids);
        dishFlavorService.remove(queryWrapper);
        //

    }
}
