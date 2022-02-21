package com.xjh.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xjh
 * @date 2022/2/13 17:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("web_user")
public class WebUser implements Serializable {

    @TableId
    private Integer id;

    private String username;

    private String password;

    private Integer roleId;
}
