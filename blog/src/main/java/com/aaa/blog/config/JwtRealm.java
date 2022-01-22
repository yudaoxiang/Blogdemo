package com.aaa.blog.config;

import com.aaa.blog.entity.User;
import com.aaa.blog.jwt.JwtToken;
import com.aaa.blog.service.UserService;
import com.aaa.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static java.awt.SystemColor.info;

/**
 * @author ydxstar
 * @create 2022-01-05 16:45
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=======执行授权方法");
        log.info("==========principal {}",principalCollection.toString());
        Claims claims = JwtUtil.decode(principalCollection.toString());
        String username = (String)claims.get("username");
        log.info("======username {}",username);
        User user = userService.getbyUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission(user.getPerms());
        log.info(" user.getPerms() {}",user.getPerms());
        return  info;
    }

    @Override
    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("======执行认证方法");
        String jwt = (String) authenticationToken.getPrincipal();
        if(jwt==null){
            throw new NullPointerException("jwt不能为空");
        }

        String username = (String)JwtUtil.decode(jwt).get("username");
       if(username==null){
           throw new AuthenticationException("token无效");
       }
        User user = userService.getbyUsername(username);
       if(user==null){
           throw new AuthenticationException("用户不存在");
       }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(jwt,jwt,getName());
        log.info("=============info {}",info);
        return info;
    }
}
