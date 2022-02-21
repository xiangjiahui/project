package com.xjh.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xjh
 * @date 2022/2/13 17:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("web_role")
public class WebRole implements Serializable {

    private Integer id;

    private String roleName;

    private Date createDateTime;

    private Date updateDateTime;

    private Integer authId;
}
