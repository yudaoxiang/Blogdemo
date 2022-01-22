package com.aaa.blog.filter;

import com.aaa.blog.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ydxstar
 * @create 2022-01-09 17:27
 */
@Slf4j
public class JwtFilter extends AccessControlFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = httpServletRequest.getHeader("Authorization");
        if(jwt==null){
            return true;
        }
        JwtToken jwtToken=  new JwtToken(jwt);
        try{
            getSubject(servletRequest,servletResponse).login(jwtToken);


        }catch (Exception e){
          e.printStackTrace();
          return false;
        }

        return true;
    }


}
