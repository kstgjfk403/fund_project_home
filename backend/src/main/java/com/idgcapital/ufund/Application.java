package com.idgcapital.ufund;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by yi_zhang on 2017/12/7.
 */
@SpringBootApplication
@MapperScan("com.idgcapital.ufund")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
