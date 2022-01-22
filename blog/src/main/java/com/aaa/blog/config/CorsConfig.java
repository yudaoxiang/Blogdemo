package com.aaa.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ydxstar
 * @create 2022-01-07 10:31
 */
//解决跨域问题
@Configuration
public class CorsConfig implements WebMvcConfigurer {

        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                    .allowCredentials(true)
                    .maxAge(3600)
                    .allowedHeaders("*");
        }
}
