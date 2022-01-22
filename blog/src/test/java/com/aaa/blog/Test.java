package com.aaa.blog;

import com.aaa.blog.entity.Blog;
import com.aaa.blog.mapper.BlogMapper;
import com.aaa.blog.service.BlogService;
import com.aaa.blog.service.impl.BlogServiceImpl;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author ydxstar
 * @create 2022-01-11 11:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogMapper blogMapper;

    @org.junit.Test
    public void test(){
        List<Blog> blogs = blogService.selectAllBlog(1, 1);
        System.out.println(blogs);
        List<Blog> blogs1 = blogMapper.selectAll();
        log.info(" ==========blogs {}",blogs1);
    }


}
