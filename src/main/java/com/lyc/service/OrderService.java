package com.lyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyc.domain.Orders;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/23 9:49
 */

public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
