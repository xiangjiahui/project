package com.xjh.gamestory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.gamestory.domain.Cart;
import com.xjh.gamestory.vo.CartVO;

import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:26
 */
public interface CartService extends IService<Cart> {

    /**
     * 添加商品到购物车
     *
     * @param uid    登录用户的uid
     * @param pid    商品的pid
     * @param number 商品增加的数量
     */
    void addToCart(Integer uid, Integer pid, Integer number);

    /**
     * 获得所有的购物车信息
     *
     * @param uid 登录用户的uid
     * @return 根据登录用户所得的购物车信息列表
     */
    List<CartVO> getAllCart(Integer uid);

    /**
     * 增加商品数量
     *
     * @param cid  商品cid
     * @param uid  登录用户uid
     * @param username 登录用户username
     * @return 增加的商品数量
     */
    Integer addNumber(Integer cid, Integer uid, String username);

    /**
     * 删除商品数量
     *
     * @param cid  商品cid
     * @param uid  登录用户uid
     * @param username 登录用户username
     * @return 删除的商品数量
     */
    Integer reduceNumber(Integer cid, Integer uid, String username);
}
