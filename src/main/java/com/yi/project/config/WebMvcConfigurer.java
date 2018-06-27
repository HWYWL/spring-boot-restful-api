package com.yi.project.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yi.project.core.Result;
import com.yi.project.core.ResultCode;
import com.yi.project.core.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Spring MVC 配置
 * @author YI
 * @date 2018-6-27 16:30:17
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    /**
     * 当前配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 使用阿里 FastJson 作为JSON MessageConverter
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        MediaType textPlain = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
        MediaType textHtml = new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8"));
        MediaType urlenCodedutf8 = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("UTF-8"));
        supportedMediaTypes.add(textHtml);
        supportedMediaTypes.add(textPlain);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(urlenCodedutf8);

        //保留空的字段
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                //String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                //Number null -> 0
                SerializerFeature.WriteNullNumberAsZero);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));

        converter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(converter);
    }


    /**
     * 统一异常处理
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e)-> {
                Result result = new Result();
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;

                    //业务失败的异常，如“账号或密码错误”
                    if (e instanceof ServiceException) {
                        result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                        logger.info(e.getMessage());
                    } else {
                        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                        String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                        logger.error(message, e);
                    }
                } else {
                    if (e instanceof NoHandlerFoundException) {
                        result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                    } else {
                        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
                        logger.error(e.getMessage(), e);
                    }
                }
                responseResult(response, result);
                return new ModelAndView();
        });
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**");
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，在项目中建议使用Token代替。
        //开发环境忽略签名认证
        if (!StringUtils.contains(env, "dev")) {
            registry.addInterceptor(new HandlerInterceptorAdapter() {

                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    String sign = request.getParameter("sign");
                    //验证签名
                    if (StringUtils.isNotEmpty(sign) && validateSign(request, sign)) {
                        return true;
                    } else {
                        logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                                request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                        Result result = new Result();
                        result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                        responseResult(response, result);
                        return false;
                    }
                }
            });
        }
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 一个简单的签名认证，规则：请求参数按ASCII码排序后，拼接为a=value&b=value...这样的字符串后进行MD5
     *
     * @param request
     * @param requestSign
     * @return
     */
    private boolean validateSign(HttpServletRequest request, String requestSign) {
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        Collections.sort(keys);

        String linkString = "";

        for (String key : keys) {
            if (!"sign".equals(key)) {
                linkString += key + "=" + request.getParameter(key) + "&";
            }
        }
        if (StringUtils.isEmpty(linkString)) {
            return false;
        }

        linkString = linkString.substring(0, linkString.length() - 1);
        //自己修改
        String key = "Potato";
        String sign = DigestUtils.md5Hex(linkString + key);

        return StringUtils.equals(sign, requestSign);

    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}
