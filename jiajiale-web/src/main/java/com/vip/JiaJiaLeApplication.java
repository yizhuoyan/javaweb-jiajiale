package com.vip;

import com.vip.dto.AccountContext;
import com.vip.service.VipManageService;
import com.vip.service.impl.VipManageServiceImpl;
import com.vip.web.controller.spring.StringTrimConverter;
import com.vip.web.interceptor.SessionInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Created by Administrator on 5/14.
 */
@MapperScan("com.vip.dao")
@SpringBootApplication
public class JiaJiaLeApplication implements WebMvcConfigurer {

    /**
     * 验证器
     *
     * @return
     */
    @Bean
    public Validator createValidator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }



    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加字符串清理coverter
        registry.addConverter(new StringTrimConverter());
    }


    /**
     * 添加权限拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/session");
    }

    /**
     * 添加跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowCredentials(true)
                .allowedMethods("*");
    }


    public static void main(String[] args) {
        SpringApplication.run(JiaJiaLeApplication.class);
    }


}
