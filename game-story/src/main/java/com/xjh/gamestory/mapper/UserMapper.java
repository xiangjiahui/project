package com.xjh.gamestory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.gamestory.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xjh
 * @date 2022/2/17 15:19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
