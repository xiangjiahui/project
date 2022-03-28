package com.xjh.gamestory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.gamestory.domain.District;

import java.util.List;


/**
 * @author xjh
 * @date 2022/2/17 15:20
 */
public interface DistrictService extends IService<District> {

    /**
     * 获得省市列表
     * @param parent 父节点id
     * @return 所有的列表
     */
    List<District> getDistrictByParent(String parent);

    /**
     * 获得省市的name
     * @param code 省市的code
     * @return 省市的名字
     */
    String getNameByCode(String code);

}
