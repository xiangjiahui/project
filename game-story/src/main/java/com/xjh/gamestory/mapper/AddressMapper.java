package com.xjh.gamestory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.gamestory.domain.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xjh
 * @date 2022/2/17 15:16
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 统计某用户的收货地址数据的数量
     * @param uid 用户的uid
     * @return
     */
    Integer countByUid(Integer uid);
}
