package com.hoos.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hoos.mybaisplus.quickstart.mapper")
public class MybaisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybaisPlusApplication.class, args);
    }

}
