package com.xjh.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.web.domain.WebUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xjh
 * @date 2022/2/13 17:34
 */
@Mapper
public interface WebUserMapper extends BaseMapper<WebUser> {
}
