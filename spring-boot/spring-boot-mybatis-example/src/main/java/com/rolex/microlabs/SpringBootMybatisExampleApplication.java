package com.rolex.microlabs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rolex.microlabs.dao") // 和@Mapper二选一
public class SpringBootMybatisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisExampleApplication.class, args);
    }

}
