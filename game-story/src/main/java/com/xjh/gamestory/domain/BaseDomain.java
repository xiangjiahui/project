package com.xjh.gamestory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * @author 123
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDomain implements Serializable {

    private String createdUser;

    private Date createdTime;

    private String modifiedUser;

    private Date modifiedTime;


}
