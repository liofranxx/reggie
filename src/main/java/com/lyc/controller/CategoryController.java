package com.lyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyc.common.Result;
import com.lyc.domain.Category;
import com.lyc.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/13 15:59
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
@SuppressWarnings("all")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品或套餐
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Category category){
        log.info("新增分类，分类信息：{}", category.toString());

        if (categoryService.save(category)){
            return Result.success("新增分类成功");
        } else {
            return Result.error("新增分类失败");
        }

    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return 一页所要展示的内容
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        log.info("page={}, pageSize={}", page, pageSize);
        // 构造分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        // 构造条件构造器
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据sort进行排序
        wrapper.orderByAsc(Category::getSort);
        // 执行查询
        categoryService.page(pageInfo, wrapper); // 内部会处理封装到pageInfo中

        return Result.success(pageInfo);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("ids") Long id){ // RequestParam将id和前端页面传递过来的参数绑定，因为两者不同
        log.info("删除分类，分类id为{}", id);
        categoryService.remove(id);
        return Result.success("删除分类信息成功");
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

        return Result.success("修改分类信息成功");
    }

    @GetMapping("/list")
    public Result<List<Category>> list(Category category){ // 将参数封装到类中
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(category.getType()!=null, Category::getType, category.getType());
        wrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(wrapper);
        return Result.success(list);
    }
}
