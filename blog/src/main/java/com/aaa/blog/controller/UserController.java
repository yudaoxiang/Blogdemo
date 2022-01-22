package com.aaa.blog.controller;


import cn.hutool.core.map.MapUtil;
import com.aaa.blog.common.Result;
import com.aaa.blog.dto.LoginDTO;
import com.aaa.blog.entity.User;
import com.aaa.blog.service.UserService;

import com.aaa.blog.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author ydxstar
 * @create 2022-01-06 12:04
 */
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping ("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO , ServletResponse servletResponse) {

        User user = userService.getbyUsername(loginDTO.getUsername());

        if (user == null) {
            throw new UnknownAccountException("用户名错误");
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new IncorrectCredentialsException("密码错误");
        }
        String jwtToken = JwtUtil.getJwtToken(loginDTO.getUsername(), loginDTO.getPassword());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Authorization",jwtToken);
        response.setHeader("Access-control-Expose-Headers","Authorization");


        return Result.succ(200, "登录成功", MapUtil
                .builder()
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .put("avatar", user.getAvatar())
                .put("id", user.getId())
                .map());
    }

    @RequiresAuthentication
    @RequestMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(200,"退出成功",null);
    }
    @PostMapping("/user")
    @RequiresPermissions("user:add")
    public Result test(@RequestBody @Validated LoginDTO loginDTO){
        User user = userService.getbyUsername(loginDTO.getUsername());
        return Result.succ(200, "登录成功", MapUtil
                .builder()
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .put("avatar", user.getAvatar())
                .put("id", user.getId())
                .map());
    }


}
