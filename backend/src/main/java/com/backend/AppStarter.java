package com.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序类
 */
@SpringBootApplication
//=
//@SpringBootConfiguration+
//@EnableAutoConfiguration+
//ComponentScan("com.sblearning")
@MapperScan(basePackages = "com.sblearning")
public class AppStarter {
    public static void main(String[] args) {

        SpringApplication.run(AppStarter.class, args);
    }
}
