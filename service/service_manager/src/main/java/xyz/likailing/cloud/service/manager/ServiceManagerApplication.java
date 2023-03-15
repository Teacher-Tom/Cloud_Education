package xyz.likailing.cloud.service.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author derek
 * @since 2023-03-11
 */
@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagerApplication.class,args);
    }
}