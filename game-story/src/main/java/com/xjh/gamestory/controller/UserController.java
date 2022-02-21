package com.xjh.gamestory.controller;

import com.xjh.gamestory.domain.User;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.service.UserService;
import com.xjh.gamestory.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 向嘉晖
 * @date 2022/2/19
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public JsonResult register(User user) {
        try {
            userService.register(user);
            return JsonResult.success("注册成功", user);
        } catch (UsernameDuplicateException e) {
            return JsonResult.failed("用户名被占用", user);
        } catch (InsertException e) {
            //插入异常
            return JsonResult.error("注册失败", user);
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password, HttpSession session) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("uid", user.getUid());
            session.setAttribute("username", user.getUsername());
            return JsonResult.success("登录成功", user);
        } catch (PasswordNotMatchException e) {
            return JsonResult.failed("密码错误");
        } catch (UserNotFoundException e) {
            return JsonResult.error("用户名不存在");
        }

    }


    @PostMapping("/change_password")
    @ResponseBody
    public JsonResult changePassword(String oldPassword, String newPassword, HttpSession session) {
        try {
            userService.updatePassword(getUidFromSession(session), getUsernameFromSession(session),
                    oldPassword, newPassword);
            return JsonResult.success();
        } catch (PasswordNotMatchException e) {
            return JsonResult.failed("原密码错误!");
        } catch (UpdateException e) {
            return JsonResult.error();
        }
    }

    @GetMapping("/getUserById")
    @ResponseBody
    public JsonResult getUser(HttpSession session) {
        try {
            User user = userService.getUserById(getUidFromSession(session));
            return JsonResult.success("查询用户成功!", user);
        } catch (UserNotFoundException e) {
            return JsonResult.error("查询用户失败,不存在该用户!");
        }

    }


    @PostMapping("/change_user")
    @ResponseBody
    public JsonResult changeUser(String phone, String email, Integer gender, HttpSession session) {
        try {
            userService.updateUser(getUidFromSession(session), getUsernameFromSession(session),
                    phone, email, gender);
            return JsonResult.success("更新用户信息成功！");
        } catch (UpdateException e) {
            return JsonResult.failed();
        } catch (UserNotFoundException e) {
            return JsonResult.error("没有此用户信息!");
        }
    }

}
