package com.aaa.blog.util;



import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import java.util.*;

/**
 * @author ydxstar
 * @create 2022-01-09 15:42
 */
public class JwtUtil {

   private  static final String defaultSecretKey="yudaoxiang1993";

    private  static final  long EXPTIME=5*60*60*1000;

    private  static final SignatureAlgorithm defaultSignatureAlgorithm=SignatureAlgorithm.HS256;
    public JwtUtil(){

    }
    /*
     *这里就是产生jwt字符串的地方
     * jwt字符串包括三个部分
     *  1. header
     *      -当前字符串的类型，一般都是“JWT”
     *      -哪种算法加密，“HS256”或者其他的加密算法
     *      所以一般都是固定的，没有什么变化
     *  2. payload
     *      一般有四个最常见的标准字段（下面有）
     *      iat：签发时间，也就是这个jwt什么时候生成的
     *      jti：JWT的唯一标识
     *      iss：签发人，一般都是username或者userId
     *      exp：过期时间
     *
     * */
    public static  String getJwtToken( String username,String password){

        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
             long expMillis = nowMillis + EXPTIME;
             Date exp = new Date(expMillis); //超时时间

        /**
         *
        HashMap<String, Object> header = new HashMap<>();
        header.put("jyp","JWT");
        header.put("alg","HS256");
       return JWT.create()
                .withHeader(header)
                .withClaim("username",username)
                .withClaim("password",password)
                .withIssuedAt(date)
                .withExpiresAt(exp)
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
         */

        JwtBuilder builder= Jwts.builder()
                 .setHeaderParam("jyp","JWT")
                 .setHeaderParam("alg","HS256")
                 .claim("username",username)
                .claim("password",password)
                 .setIssuedAt(date)
                 .setExpiration(exp)
                 .setId(UUID.randomUUID().toString())
                 .signWith(defaultSignatureAlgorithm,defaultSecretKey);

         return builder.compact();


    }

    public static Claims decode(String jwtToken){
        Claims claims = Jwts.parser().setSigningKey(defaultSecretKey).parseClaimsJws(jwtToken).getBody();
        return claims;

    }

    //判断jwtToken是否合法
    public static boolean isExpiration(String jwtToken) {
    try {
       return decode(jwtToken)
                .getExpiration()
                .before(new Date());

    }catch (ExpiredJwtException e){
        return true;
    }
    }
}
