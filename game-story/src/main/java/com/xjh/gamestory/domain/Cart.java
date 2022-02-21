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
 * 购物车数据的实体类
 * @author 123
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_cart")
public class Cart extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer cid;

    @TableField("uid")
    private Integer uid;

    @TableField("pid")
    private Integer pid;

    @TableField("price")
    private Long price;

    @TableField("num")
    private Integer num;

}
