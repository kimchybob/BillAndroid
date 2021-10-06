package com.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主程序类
 */
//=
//@SpringBootConfiguration+
//@EnableAutoConfiguration+
//ComponentScan("com.backend")
@SpringBootApplication

@MapperScan(basePackages = "com.backend")
public class AppStarter {
    public static void main(String[] args) {

        SpringApplication.run(AppStarter.class, args);
    }


}
