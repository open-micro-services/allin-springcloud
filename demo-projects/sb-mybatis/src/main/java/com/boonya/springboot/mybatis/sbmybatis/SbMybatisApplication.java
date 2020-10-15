package com.boonya.springboot.mybatis.sbmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boonya.springboot.mybatis.sbmybatis.mapper")
public class SbMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbMybatisApplication.class, args);
    }

}
