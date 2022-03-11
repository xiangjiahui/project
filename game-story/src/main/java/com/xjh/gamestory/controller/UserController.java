package com.xjh.gamestory.controller;

import com.xjh.gamestory.domain.User;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.service.UserService;
import com.xjh.gamestory.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /* 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @PostMapping("/change_avatar")
    @ResponseBody
    public JsonResult changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        // getSize()：返回文件的大小，以字节为单位
        if (file.getSize() > AVATAR_MAX_SIZE) {
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        File nowFIle = new File("");
        String parent = nowFIle.getCanonicalPath() + "\\src\\main\\resources\\static\\upload";


        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        userService.updateAvatar(getUidFromSession(session),getUsernameFromSession(session),avatar);

        return JsonResult.success(avatar);
    }

}
