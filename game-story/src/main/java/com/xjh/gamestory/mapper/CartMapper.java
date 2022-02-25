package com.xjh.gamestory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.gamestory.domain.Cart;
import com.xjh.gamestory.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:17
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * 查询所有的购物车信息
     * @param uid 登录的用户uid
     * @return CartVO对象
     */
    List<CartVO> findVOById(Integer uid);
}
