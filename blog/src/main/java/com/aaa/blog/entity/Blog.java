package com.aaa.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author ydxstar
 * @create 2022-01-04 16:12
 */
@Data
@Component
public class Blog {
    private Integer id;
    @NotBlank(message = "标题不能为空")
    private  String title;
    @NotBlank(message = "内容不能为空")
    private String content;
    @NotBlank(message = "摘要不能为空")
    private String summary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatetime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createtime;
}
