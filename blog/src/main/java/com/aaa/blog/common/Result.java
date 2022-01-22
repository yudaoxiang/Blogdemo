package com.aaa.blog.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ydxstar
 * @create 2022-01-07 9:15
 */
@Data
public class Result implements Serializable {
    private  int code;//返回状态码 200表示成功，400表示失败
    private  String message;
    private  Object data;

    public static Result succ(int code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return  result;
    }
    public static Result succ(Object data){
        return succ(200,"操作成功",data);
    }
    public static Result fail(int code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return  result;
    }
    public static Result fail(String message, Object data){
        return succ(400,message,data);
    }
    public static Result fail(String message){
        return fail(400,message,null);
    }
}
