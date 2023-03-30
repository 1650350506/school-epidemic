package com.hmdp.controller;


import cn.hutool.core.util.ObjectUtil;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Register;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.dto.UserLoginDTO;
import com.hmdp.entity.User;
import com.hmdp.service.IUserInfoService;
import com.hmdp.service.IUserService;
import com.hmdp.utils.JwtUtils;
import com.hmdp.utils.UserHolder;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.hmdp.vo.PermissionCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @Resource
    private IUserInfoService userInfoService;

    @CrossOrigin
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) {

        String url = userService.uploadFileAvatar(file);
        System.out.println(url);
        return url;
    }

    /**
     * 发送手机验证码
     */
    @PostMapping("code")
    public Result sendCode(@RequestParam("phone") String phone, HttpSession session) {
        // TODO 发送短信验证码并保存验证码
        return userService.sendCode(phone,session);
    }

    /**
     * 登录功能
     * @param loginForm 登录参数，包含手机号、验证码；或者手机号、密码
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginForm, HttpSession session)
        throws IOException {
        // TODO 实现登录功能
        return userService.login(loginForm,session);
    }
    /***
     * @Description: 账号密码登录
     * @Author: zcy
     * @Date: 2022/9/21 15:14
     * @Param: [userLogin]
     * @Return: com.hmdp.dto.Result
     */
    @PostMapping("/userlogin")
    public Result userLogin (@RequestBody UserLoginDTO userLogin){
        return userService.userLogin(userLogin);
    }
   /**
    * @Description: 账号注册
    * @Author: zcy
    * @Date: 2022/9/22 16:03
    * @Param: [register]
    * @Return: com.hmdp.dto.Result
    */
    @PostMapping("/register")
    public Result userRegister(@RequestBody Register register){
        return userService.userRegister(register);
    }
    /**
     * 登出功能
     * @return 无
     */
    @DeleteMapping("/logout")
    public Result logout(HttpServletRequest request){


        // TODO 实现登出功能


        return userService.logout(request);
    }

    @GetMapping("/me")
    public Result me(){
        // TODO 获取当前登录的用户并返回
        UserDTO user = UserHolder.getUser();
        return Result.ok(user);
    }


    @GetMapping("/listMenu")
    public Result  listMenu(HttpServletRequest servletRequest, HttpServletResponse response){
        String auth = servletRequest.getHeader("auth");
        User user = JwtUtils.check(auth);
        if(ObjectUtil.isEmpty(user)){
            return Result.fail("用户不是工作人员");
        }
        PermissionCodeVO permissionCodeVO = userInfoService.listMenu(user);
        return Result.ok(permissionCodeVO);

    }
}
