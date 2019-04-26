package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import util.IdWorker;

/**
 * @author hftang
 * @date 2019-04-25 17:51
 * @desc base 启动类
 */
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
    //要用到雪花算法的 分布式id
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }


}
