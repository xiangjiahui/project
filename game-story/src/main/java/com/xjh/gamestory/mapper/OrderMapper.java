package com.xjh.gamestory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.gamestory.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xjh
 * @date 2022/2/17 15:19
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
