package com.lyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.dao.DishFlavorDao;
import com.lyc.domain.DishFlavor;
import com.lyc.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/14 20:57
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorDao, DishFlavor> implements DishFlavorService {
}
