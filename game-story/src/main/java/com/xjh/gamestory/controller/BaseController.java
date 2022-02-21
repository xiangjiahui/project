package com.xjh.gamestory.controller;

import com.xjh.gamestory.common.Convert;

import javax.servlet.http.HttpSession;

/**
 * @author 向嘉晖
 * @date 2022/2/20
 */
public class BaseController {

    protected final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(Convert.toStr(session.getAttribute("uid")));
    }

    protected final String getUsernameFromSession(HttpSession session){
        return Convert.toStr(session.getAttribute("username"));
    }
}
