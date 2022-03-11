package com.xjh.gamestory.controller;

import com.xjh.gamestory.domain.Product;
import com.xjh.gamestory.exception.ProductNotFoundException;
import com.xjh.gamestory.service.ProductService;
import com.xjh.gamestory.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向嘉晖
 * @date 2022/2/25
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;

    @GetMapping("/hot_list")
    @ResponseBody
    public JsonResult getAllProduct() {
        try {
            List<Product> products = productService.getProducts();
            return JsonResult.success(products);
        } catch (ProductNotFoundException e) {
            return JsonResult.error();
        }
    }


    @GetMapping("/details/{id}")
    @ResponseBody
    public JsonResult getProductDetails(@PathVariable(name = "id") Integer id) {
        try {
            Product product = productService.getProductById(id);
            return JsonResult.success(product);
        } catch (ProductNotFoundException e) {
            return JsonResult.error("没有发现该商品");
        }
    }
}
