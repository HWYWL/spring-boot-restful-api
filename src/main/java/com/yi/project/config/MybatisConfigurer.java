package com.yi.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Mybatis & Mapper & PageHelper 配置
 * @author YI
 * @date 2018-6-27 16:26:12
 */
@Configuration
@MapperScan(basePackages="com.yi.project.dao")
@ComponentScan(basePackages= {"com.yi.project"})
public class MybatisConfigurer {

}

