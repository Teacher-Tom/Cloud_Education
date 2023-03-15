package xyz.likailing.cloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@MapperScan("xyz.likailing.cloud.service.mapper")
public class ServiceAlls {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAlls.class, args);
    }
}
