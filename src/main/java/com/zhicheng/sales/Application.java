package com.zhicheng.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootConfiguration
@EnableSwagger2
@ComponentScan(
        basePackages = {"com.zhicheng.sales"}
)
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
