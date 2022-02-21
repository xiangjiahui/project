package com.xjh.gamestory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.gamestory.domain.User;

/**
 * @author xjh
 * @date 2022/2/17 15:29
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param user 注册的用户信息
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录的用户
     */
    User login(String username, String password);

    /**
     * 修改密码
     *
     * @param uid         登录时session存储的uid
     * @param username    登录时session存储的username
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void updatePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 获得用户的资料
     *
     * @param uid 登录用户的id
     * @return 登录用户
     */
    User getUserById(Integer uid);

    /**
     * 更新用户电话和邮件信息
     *
     * @param uid      登录的用户uid
     * @param username 登录的用户名
     * @param phone    修改的电话
     * @param email    修改的邮件
     * @param gender   修改的性别
     */
    void updateUser(Integer uid, String username, String phone, String email, Integer gender);
}
