package com.lyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyc.dao.OrderDetailDao;
import com.lyc.domain.OrderDetail;
import com.lyc.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author lyc
 * @version 1.0
 * @project reggie
 * @time 2023/8/23 9:51
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {
}
