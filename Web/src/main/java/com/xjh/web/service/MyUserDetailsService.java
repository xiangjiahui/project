package com.xjh.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjh.web.domain.WebAuthrity;
import com.xjh.web.domain.WebRole;
import com.xjh.web.domain.WebUser;
import com.xjh.web.mapper.WebAuthrityMapper;
import com.xjh.web.mapper.WebRoleMapper;
import com.xjh.web.mapper.WebUserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author xjh
 * @date 2022/2/13 17:29
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private WebUserMapper userMapper;

    @Autowired
    private WebAuthrityMapper authrityMapper;

    @Autowired
    private WebRoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<WebUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        WebUser user = userMapper.selectOne(wrapper);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        QueryWrapper<WebRole> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("id",user.getRoleId());
        WebRole webRole = roleMapper.selectOne(roleWrapper);


        QueryWrapper<WebAuthrity> authWrapper = new QueryWrapper<>();
        authWrapper.eq("id",webRole.getAuthId());
        WebAuthrity webAuthrity = authrityMapper.selectOne(authWrapper);

        //设置权限
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList
                ("admin,ROLE_admin");

        return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);

    }
}
