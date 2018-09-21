package com.demo.conf;

import com.apidoc.common.Const;
import com.apidoc.service.ApiDocService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

/**
 * 配置类
 * 让springboot自动扫描并管理apidoc工具下的所有class
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.apidoc")
@MapperScan("com.apidoc.dao")
@EntityScan("com.apidoc.entity")
public class ApiDocConfig {
    @Bean
    public ApiDocService generator() {
        Const.codePath = Const.projectPath + "readingRoom-apidoc" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        return new ApiDocService();
    }
}
