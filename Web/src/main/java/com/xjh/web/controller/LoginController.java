package com.xjh.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xjh
 * @date 2022/2/13 16:59
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("uname","XJH");
        return mv;
    }

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addObject("loginName","admin");
        return mv;
    }

    @GetMapping("/403")
    public ModelAndView authError(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("403");
        mv.addObject("authMsg","权限不足,请联系管理员添加访问权限");
        return mv;
    }

    @GetMapping("/main")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        return mv;
    }

}
