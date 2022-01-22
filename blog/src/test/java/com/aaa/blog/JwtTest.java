package com.aaa.blog;

import com.aaa.blog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @author ydxstar
 * @create 2022-01-10 14:12
 */

public class JwtTest {
    @Test
    public void getJwt(){
        String token = JwtUtil.getJwtToken("zhangsan", "123456");
        System.out.println(token);
        Claims decode = JwtUtil.decode(token);
        System.out.println(decode);
        boolean verify = JwtUtil.isExpiration(token);
        System.out.println(verify);
    }
}
