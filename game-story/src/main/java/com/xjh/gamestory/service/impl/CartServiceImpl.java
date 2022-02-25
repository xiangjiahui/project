package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Cart;
import com.xjh.gamestory.domain.Product;
import com.xjh.gamestory.domain.User;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.mapper.CartMapper;
import com.xjh.gamestory.mapper.ProductMapper;
import com.xjh.gamestory.mapper.UserMapper;
import com.xjh.gamestory.service.CartService;
import com.xjh.gamestory.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:26
 */
@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer number) {
        //根据pid查询商品的信息
        QueryWrapper<Product> productWrapper = new QueryWrapper<>();
        productWrapper.eq("id", pid);
        Product product = productMapper.selectOne(productWrapper);

        //根据uid查询登录的用户,给cart对象设置CreatedUser
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("uid", uid);
        User loginUser = userMapper.selectOne(userWrapper);

        Cart cart = new Cart();
        cart.setPid(product.getId());
        cart.setUid(loginUser.getUid());
        cart.setNum(number);
        cart.setPrice(product.getPrice());
        cart.setCreatedTime(new Date());
        cart.setCreatedUser(loginUser.getUsername());

        int row = cartMapper.insert(cart);

        if (row != 1) {
            throw new InsertException("插入数据失败");
        }
    }

    @Override
    public List<CartVO> getAllCart(Integer uid) {
            return cartMapper.findVOById(uid);
    }

    @Override
    public Integer addNumber(Integer cid, Integer uid, String username) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",cid).eq("uid",uid);
        Cart resultCart = cartMapper.selectOne(wrapper);

        if (resultCart == null){
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!resultCart.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = resultCart.getNum() + 1;

        Cart cart = new Cart();
        cart.setCid(cid);
        cart.setModifiedTime(new Date());
        cart.setNum(num);

        int row = cartMapper.updateById(cart);

        if (row != 1){
            throw new UpdateException("修改商品数量时出现未知错误");
        }

        return num;

    }

    @Override
    public Integer reduceNumber(Integer cid, Integer uid, String username) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",cid).eq("uid",uid);
        Cart resultCart = cartMapper.selectOne(wrapper);

        if (resultCart == null){
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!resultCart.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = resultCart.getNum() - 1;

        if (num < 1){
            throw new DeleteException("删除错误,商品数量最少为1");
        }

        Cart cart = new Cart();
        cart.setCid(cid);
        cart.setModifiedTime(new Date());
        cart.setNum(num);

        int row = cartMapper.updateById(cart);

        if (row != 1){
            throw new UpdateException("修改商品数量时出现未知错误");
        }

        return num;
    }
}
