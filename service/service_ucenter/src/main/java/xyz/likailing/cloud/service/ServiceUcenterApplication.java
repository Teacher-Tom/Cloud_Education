package xyz.likailing.cloud.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author helen
 * @since 2020/4/29
 */
@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@EnableDiscoveryClient
@MapperScan("xyz.likailing.cloud.service.mapper")
public class ServiceUcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterApplication.class, args);
    }
}