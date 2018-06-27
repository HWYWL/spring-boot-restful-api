package com.yi.project.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

import static com.yi.project.core.ProjectConstant.MAPPER_INTERFACE_REFERENCE;
import static com.yi.project.core.ProjectConstant.MAPPER_PACKAGE;
import static com.yi.project.core.ProjectConstant.MODEL_PACKAGE;

/**
 * Mybatis & Mapper & PageHelper 配置
 * @author YI
 * @date 2018-6-27 16:26:12
 */
@Configuration
@MapperScan(basePackages="com.yi.project.dao")
@ComponentScan(basePackages= {"com.yi.project"})
public class MybatisConfigurer {
//    @Resource
//    private DataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage(MODEL_PACKAGE);
//
//        //分页插件
//        PageHelper pageHelper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        pageHelper.setProperties(properties);
//        Interceptor[] interceptors = {pageHelper};
//        //添加插件
//        bean.setPlugins(new Interceptor[]{pageHelper});
//
//        //添加XML目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        bean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
//        return bean.getObject();
//    }
//
//    @Configuration
//    @AutoConfigureAfter(MybatisConfigurer.class)
//    public static class MyBatisMapperScannerConfigurer {
//
//        @Bean
//        public MapperScannerConfigurer mapperScannerConfigurer() {
//            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//            mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);
//            //配置通用mappers
//            Properties properties = new Properties();
//            properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
//            properties.setProperty("notEmpty", "false");
//            properties.setProperty("IDENTITY", "MYSQL");
//            mapperScannerConfigurer.setProperties(properties);
//
//            return mapperScannerConfigurer;
//        }
//
//    }
}

