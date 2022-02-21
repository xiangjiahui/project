package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Product;
import com.xjh.gamestory.mapper.ProductMapper;
import com.xjh.gamestory.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author xjh
 * @date 2022/2/17 15:29
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
