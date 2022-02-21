package com.xjh.gamestory.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 用户数据的实体类
 * @author 123
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("gender")
    private Integer gender;

    @TableField("avatar")
    private String avatar;

    @TableField("is_delete")
    private Integer isDelete;


}
