package com.aaa.blog.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ydxstar
 * @create 2022-01-09 17:44
 */
public class JwtToken implements AuthenticationToken {
    private String jwt;
    public JwtToken(String jwt){
        this.jwt=jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
