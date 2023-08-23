package com.lyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.common.Result;
import com.lyc.domain.Category;
import com.lyc.domain.Dish;
import com.lyc.domain.DishFlavor;
import com.lyc.dto.DishDto;
import com.lyc.service.CategoryService;
import com.lyc.service.DishFlavorService;
import com.lyc.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/14 20:42
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page<DishDto>> page(int page, int pageSize, String name){
        log.info("page={}, pageSize={}", page, pageSize);
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null, Dish::getName, name);
        wrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, wrapper);

        // 对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            log.info("id={}", category );
            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return Result.success(dishDtoPage);
    }

    @PostMapping
    public Result<String> save(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return Result.success("新增菜品成功");
    }

    @GetMapping("/{id}")
    public Result<DishDto> get(@PathVariable Long id){
        log.info("id = {}", id);
        DishDto dishDto = dishService.getByIdWhitFlavor(id);

        return Result.success(dishDto);
    }

    @PutMapping
    public Result<String> update(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());
        dishService.updateWithFlavor(dishDto);
        return Result.success("修改菜品成功");
    }

    @DeleteMapping
    public Result<String> delete(@RequestParam("ids") ArrayList<Long> ids){ // TODO: 删除菜品时会影响套餐，所以需要对套餐表中包含菜品的套餐进行停售操作
        log.info("ids = {}", ids);
        // 成功取到ids，首先删除对应菜品在菜品表Dish中的记录
        // 然后删除对应菜品在口味表DishFlavor中的记录
        dishService.deleteWithFlavor(ids);
        return Result.success("删除成功");
    }


    @PostMapping("/status/{status}")
    public Result<String> status(@PathVariable Integer status, @RequestParam ArrayList<Long> ids){
        log.info("status = {}, ids = {}", status, ids);
//        LambdaQueryWrapper<Dish> wrapper =new LambdaQueryWrapper<>();
//        wrapper.in(Dish::getId, ids);
//        dishService.update();
        ids.forEach(id -> {
            Dish dish = new Dish();
            dish.setId(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        });
        return Result.success("修改状态成功");
    }

//    @GetMapping("/list")
//    public Result<List<Dish>> list(Dish dish){
//        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
//
//        // 菜品分类查询和名字模糊查询
//        wrapper.eq(dish.getCategoryId()!=null, Dish::getCategoryId, dish.getCategoryId());
//        wrapper.like(dish.getName()!=null, Dish::getName, dish.getName());
//        // 只查询起售的菜品
//        wrapper.eq(Dish::getStatus, 1);
//        // 添加排序条件
//        wrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//
//        List<Dish> list = dishService.list(wrapper);
//        return Result.success(list);
//    }

    @GetMapping("/list")
    public Result<List<DishDto>> list(Dish dish){
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();

        // 菜品分类查询和名字模糊查询
        wrapper.eq(dish.getCategoryId()!=null, Dish::getCategoryId, dish.getCategoryId());
        wrapper.like(dish.getName()!=null, Dish::getName, dish.getName());
        // 只查询起售的菜品
        wrapper.eq(Dish::getStatus, 1);
        // 添加排序条件
        wrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(wrapper);

        List<DishDto> dishDtoList = list.stream().map((item)->{
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            log.info("id={}", category );
            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId, dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(queryWrapper);
            dishDto.setFlavors(dishFlavorList);

            return dishDto;
        }).collect(Collectors.toList());

        return Result.success(dishDtoList);
    }
}
