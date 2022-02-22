package com.xjh.gamestory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.gamestory.domain.User;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.mapper.UserMapper;
import com.xjh.gamestory.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author xjh
 * @date 2022/2/17 15:30
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 根据参数user对象获取注册的用户名
        String username = user.getUsername();
        wrapper.eq("username", username);
        // 调用持久层的User selectOne方法，根据用户名查询用户数据
        User resultUser = userMapper.selectOne(wrapper);
        // 判断查询结果是否不为null
        if (resultUser != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("注册的用户名:" + username + "已被占用");
        }

        Date date = new Date();

        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        // 补全数据：isDelete(0)
        user.setIsDelete(0);
        // 补全数据：4项日志属性
        user.setCreatedUser(username);
        user.setCreatedTime(date);
        user.setModifiedUser(username);
        user.setModifiedTime(date);

        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        int row = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (row != 1) {
            // 是：插入数据时出现某种错误，则抛出InsertException异常
            throw new InsertException("添加用户数据出现未知错误，请联系系统管理员");
        }

    }

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        //查询出来的用户信息
        User resultUser = userMapper.selectOne(wrapper);

        if (resultUser == null) {
            throw new UserNotFoundException("不存在该用户");
        }

        if (resultUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在!");
        }

        //输入的密码与数据库的密码加密匹配,如果相对则正确
        String md5Password = getMd5Password(password, resultUser.getSalt());

        if (!resultUser.getPassword().equals(md5Password)) {
            throw new PasswordNotMatchException("密码验证不匹配,密码错误");
        }

        User user = new User();
        user.setUid(resultUser.getUid());
        user.setUsername(resultUser.getUsername());
        user.setAvatar(resultUser.getAvatar());

        return user;
    }

    @Override
    public void updatePassword(Integer uid, String username, String oldPassword, String newPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        User resultUser = userMapper.selectOne(wrapper);

        if (resultUser == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (resultUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        //老密码与登录用户的密码相比较
        if (!resultUser.getPassword().contentEquals(getMd5Password(oldPassword, resultUser.getSalt()))) {
            throw new PasswordNotMatchException("原密码错误");
        }

        String newMd5Password = getMd5Password(newPassword, resultUser.getSalt());
        User user = new User();
        user.setUid(uid);
        user.setPassword(newMd5Password);
        user.setModifiedTime(new Date());

        int row = userMapper.updateById(user);

        if (row != 1) {
            throw new UpdateException("密码修改错误!");
        }
    }

    @Override
    public User getUserById(Integer uid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        User resultUser = userMapper.selectOne(wrapper);

        if (resultUser == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (resultUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        return resultUser;
    }

    @Override
    public void updateUser(Integer uid, String username, String phone, String email, Integer gender) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("username", username);
        User loginUser = userMapper.selectOne(wrapper);

        if (loginUser == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (loginUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        User user = new User();
        user.setUid(uid);
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setModifiedTime(new Date());
        user.setModifiedUser(username);

        int row = userMapper.updateById(user);

        if (row != 1) {
            throw new UpdateException("更新用户数据失败");
        }

    }

    @Override
    public void updateAvatar(Integer uid, String username, String avatar) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("username", username);
        User loginUser = userMapper.selectOne(wrapper);

        if (loginUser == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (loginUser.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        User user = new User();
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        user.setAvatar(avatar);

        int row = userMapper.updateById(user);

        if (row != 1){
            throw new UpdateException("文件上传失败");
        }
    }


    /**
     * 执行密码加密,
     *
     * @param password 原始密码
     * @param salt     加密盐值
     * @return 加密后的密文
     */
    public String getMd5Password(String password, String salt) {

        /*
         * 加密的规则:
         *  1、无视原始密码的强度
         *  2、使用UUID作为盐值，在原始密码的左右两侧拼接
         *  3、循环加密3次
         */

        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
