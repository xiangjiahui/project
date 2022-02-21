package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Order;
import com.xjh.gamestory.mapper.OrderMapper;
import com.xjh.gamestory.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author xjh
 * @date 2022/2/17 15:27
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
