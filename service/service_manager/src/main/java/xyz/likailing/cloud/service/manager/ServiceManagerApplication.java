package xyz.likailing.cloud.service.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author derek
 * @since 2023-03-11
 */
@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class ServiceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class,args);
    }
}
