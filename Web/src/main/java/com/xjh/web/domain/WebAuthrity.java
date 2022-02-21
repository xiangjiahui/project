package com.xjh.web.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xjh
 * @date 2022/2/13 17:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("web_authrity")
public class WebAuthrity implements Serializable {

    private Integer id;

    private String authName;
}
