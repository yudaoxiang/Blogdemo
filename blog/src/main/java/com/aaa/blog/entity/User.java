package com.aaa.blog.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Blob;

/**
 * @author ydxstar
 * @create 2022-01-04 16:08
 */
@Data
@Component
public class User implements Serializable {
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    @Email
    @NotBlank(message = "email不能为空")
    private  String email;
    private Blob avatar;
    private String perms;
}
