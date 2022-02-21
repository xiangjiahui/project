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
 * 商品数据的实体类
 * @author 123
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class Product extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("category_id")
    private Integer categoryId;

    @TableField("item_type")
    private String itemType;

    @TableField("title")
    private String title;

    @TableField("sell_point")
    private String sellPoint;

    @TableField("price")
    private Long price;

    @TableField("num")
    private Integer num;

    @TableField("image")
    private String image;

    @TableField("status")
    private Integer status;

    @TableField("priority")
    private Integer priority;


}
