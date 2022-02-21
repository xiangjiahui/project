package com.xjh.gamestory.domain;

;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



/**
 * 省/市/区数据的实体类
 * @author 123
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_dic_district")
public class District implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("parent")
    private String parent;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

}
