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
 * 订单中的商品数据
 * @author 123
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order_item")
public class OrderItem extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("oid")
    private Integer oid;

    @TableField("pid")
    private Integer pid;

    @TableField("title")
    private String title;

    @TableField("image")
    private String image;

    @TableField("price")
    private Long price;

    @TableField("num")
    private Integer num;

}
