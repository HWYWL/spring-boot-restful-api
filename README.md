# spring-boot-restful-api
一个基于Spring Boot &amp; MyBatis的纯净项目，用于快速构建中小型RESTful API项目~

### 简介
这个项目用于快速构建中小型RESTful API项目，摆脱重复造轮子的的过程，专注于业务代码，少加班，多陪女朋友。

### 特征&提供

- 统一响应结果封装处理
- 统一异常拦截处理
- 接口签名认证
- 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件,业务表单干净简介
- 提供代码生成器可以根据表名生成对应的Model、Mapper、Mapper.xml、Service、ServiceImpl、Controller等基础代码

### 快速开始
1. 克隆项目
2. 对```com.yi.project.codeGenerator```包内的代码生成器```CodeGenerator```进行配置，主要是JDBC，因为要根据表名来生成代码
3. 如果想自己试试的话可以使用```src/test/sql```目录下的```结构和测试数据.sql```
3. 输入表名，运行```CodeGenerator.mian()```方法，生成基础代码，如果没有出来刷新一下
4. 根据业务在基础代码上进行扩展
5. 对开发环境配置文件```application-dev.properties```进行配置，启动项目，嘤嘤嘤！

图解：项目默认的包名是 com.yi.project，如需修改请看下图
代码生成器 CodeGenerator.java
![](https://i.imgur.com/UiXds9c.jpg)

Mybatis配置加载 MybatisConfigurer.java
![](https://i.imgur.com/Q5GqPRB.jpg)

properties配置文件 application.properties
![](https://i.imgur.com/kCu0Bus.jpg)

### 开发建议
- 表名，应使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败使用```ServiceException("message")```抛出，由统一异常处理器来封装JSON结果，比如```throw new ServiceException("该手机号已存在")```，会直接被封装为```{"code":400,"message":"该手机号已存在"}```返回，无需自己处理，尽情抛出
- 需要工具类的话建议先从```apache-commons-*```和```hutool(划重点)```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册
 
### 文档
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- Hutool（[查看Hutool工具类使用文档](http://www.hutool.cn/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- 其他略

### 感谢大佬
- 土豆（[土豆](https://github.com/lihengming)）

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com