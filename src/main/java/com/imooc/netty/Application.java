package com.imooc.netty;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 */
@SpringBootApplication
//扫描所有需要的包，包含一些自用的工具类包所在的路径
@ComponentScan(basePackages = {"com.imooc"})
public class Application {
    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }
}
