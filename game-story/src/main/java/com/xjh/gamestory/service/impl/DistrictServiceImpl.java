package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.District;
import com.xjh.gamestory.mapper.DistrictMapper;
import com.xjh.gamestory.service.DistrictService;
import org.springframework.stereotype.Service;

/**
 * @author xjh
 * @date 2022/2/17 15:21
 */
@Service("districtService")
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

}
