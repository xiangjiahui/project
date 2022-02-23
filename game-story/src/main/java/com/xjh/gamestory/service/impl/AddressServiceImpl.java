package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Address;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.mapper.AddressMapper;
import com.xjh.gamestory.service.AddressService;
import com.xjh.gamestory.service.DistrictService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:25
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Resource(name = "districtService")
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private int maxCount;

    @Override
    public void addAddress(Integer uid, String username, Address address) {
        // 根据参数uid调用addressMapper的countByUid(Integer uid)方法，统计当前用户的收货地址数据的数量
        Integer count = addressMapper.countByUid(uid);

        // 判断数量是否达到上限值
        if (count > maxCount) {
            // 是：抛出AddressCountLimitException
            throw new AddressCountLimitException("收货地址数量已经达到上限(" + maxCount + ")！");
        }

        // 补全数据：将参数uid封装到参数address中
        address.setUid(uid);
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        // 补全数据：4项日志
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));

        int row = addressMapper.insert(address);

        if (row != 1) {
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员！");
        }
    }

    @Override
    public List<Address> getAllAddress(Integer uid) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).orderByAsc("is_default").orderByAsc("created_time");
        List<Address> addressesList = addressMapper.selectList(wrapper);

        if (addressesList.isEmpty()) {
            throw new AddressNotFoundException("该用户还没有收获地址!");
        }

        return addressesList;

    }

    @Override
    public void deleteAddress(Integer uid, Integer aid) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("aid", aid);
        Address address = addressMapper.selectOne(wrapper);

        if (address == null) {
            throw new AddressNotFoundException("没有发现该收获地址");
        }

        addressMapper.delete(wrapper);
    }

    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("aid", aid);
        Address address = addressMapper.selectOne(wrapper);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问的异常");
        }

        Address newAddress = new Address();
        newAddress.setAid(aid);
        newAddress.setIsDefault(1);
        newAddress.setModifiedTime(new Date());
        newAddress.setModifiedUser(username);

        int row = addressMapper.updateById(newAddress);

        if (row != 1) {
            throw new UpdateException("设置默认收货地址时出现未知错误");
        }
    }

}
