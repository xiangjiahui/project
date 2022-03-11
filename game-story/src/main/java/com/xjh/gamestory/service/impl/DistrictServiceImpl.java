package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.District;
import com.xjh.gamestory.mapper.DistrictMapper;
import com.xjh.gamestory.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:21
 */
@Service("districtService")
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public List<District> getDistrictByParent(String parent) {
        QueryWrapper<District> wrapper = new QueryWrapper<>();
        wrapper.eq("parent",parent).orderByAsc("code");
        return districtMapper.selectList(wrapper);
    }

    @Override
    public String getNameByCode(String code) {
        QueryWrapper<District> wrapper = new QueryWrapper<>();
        wrapper.eq("code",code);
        District district = districtMapper.selectOne(wrapper);

        return district.getName();
    }

    @Override
    public List<District> getDistricts() {
        QueryWrapper<District> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("code");
        return districtMapper.selectList(wrapper);
    }
}
