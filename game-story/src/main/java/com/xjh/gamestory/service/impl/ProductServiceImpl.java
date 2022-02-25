package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Product;
import com.xjh.gamestory.exception.ProductNotFoundException;
import com.xjh.gamestory.mapper.ProductMapper;
import com.xjh.gamestory.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:29
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getProducts() {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("priority");
        return productMapper.selectList(wrapper);
    }

    @Override
    public Product getProductById(Integer id) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Product product = productMapper.selectOne(wrapper);

        if (product == null) {
            throw new ProductNotFoundException("没有发现该商品信息");
        }

        return product;
    }
}
