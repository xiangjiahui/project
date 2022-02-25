package com.xjh.gamestory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.gamestory.domain.Product;

import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:28
 */
public interface ProductService extends IService<Product> {

    /**
     * 获得所有的商品
     *
     * @return 商品列表
     */
    List<Product> getProducts();

    /**
     * 获得商品的详细信息
     *
     * @param id 商品的id
     * @return 商品信息
     */
    Product getProductById(Integer id);
}
