package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Cart;
import com.xjh.gamestory.mapper.CartMapper;
import com.xjh.gamestory.service.CartService;
import org.springframework.stereotype.Service;

/**
 * @author xjh
 * @date 2022/2/17 15:26
 */
@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}
