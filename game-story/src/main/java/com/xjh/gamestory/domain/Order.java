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
import java.util.Date;

/**
 * 订单数据的实体类
 * @author 123
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_order")
public class Order extends BaseDomain implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer oid;

    @TableField("uid")
    private Integer uid;

    @TableField("recv_name")
    private String recvName;

    @TableField("recv_phone")
    private String recvPhone;

    @TableField("recv_province")
    private String recvProvince;

    @TableField("recv_city")
    private String recvCity;

    @TableField("recv_area")
    private String recvArea;

    @TableField("recv_address")
    private String recvAddress;

    @TableField("total_price")
    private Long totalPrice;

    @TableField("status")
    private Integer status;

    @TableField("order_time")
    private Date orderTime;

    @TableField("pay_time")
    private Date payTime;

}
