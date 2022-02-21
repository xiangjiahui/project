package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.Address;
import com.xjh.gamestory.mapper.AddressMapper;
import com.xjh.gamestory.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @author xjh
 * @date 2022/2/17 15:25
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
