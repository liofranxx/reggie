package com.lyc.dto;

import com.lyc.domain.Dish;
import com.lyc.domain.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/14 21:16
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();
    private String categoryName;
    private Integer copies;
}
