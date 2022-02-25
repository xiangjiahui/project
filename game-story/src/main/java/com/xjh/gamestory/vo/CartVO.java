package com.xjh.gamestory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 购物车数据的Value Object类
 * @author 123*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;


}
