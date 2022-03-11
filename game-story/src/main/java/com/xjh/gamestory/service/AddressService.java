package com.xjh.gamestory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.gamestory.domain.Address;

import java.util.List;

/**
 * @author xjh
 * @date 2022/2/17 15:24
 */
public interface AddressService extends IService<Address> {


    /**
     * 新增收获地址
     *
     * @param uid      登录用户的uid
     * @param username 登录用户的username
     * @param address  地址对象
     */
    void addAddress(Integer uid, String username, Address address);

    /**
     * 根据登录的用户获得其所有的用户地址
     *
     * @param uid 登录用户的uid
     * @return 得到的所有地址
     */
    List<Address> getAllAddress(Integer uid);

    /**
     * 根据登录用户的uid删除地址
     *
     * @param uid 登录用户的uid
     * @param aid 地址的aid
     */
    void deleteAddress(Integer uid, Integer aid);

    /**
     * 设置指定的地址为默认地址
     *
     * @param uid      登录用户的uid
     * @param aid      指定的地址的aid
     * @param username 登录用户的username
     */
    void setDefault(Integer uid, Integer aid, String username);
}
