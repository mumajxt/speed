package com.jxt.dataspeed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = "com.jxt.dataspeed.mapper")
@SpringBootApplication
public class DataspeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataspeedApplication.class, args);
    }


}
