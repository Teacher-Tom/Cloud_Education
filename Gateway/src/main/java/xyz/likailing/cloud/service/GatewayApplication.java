package xyz.likailing.cloud.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author 12042
 * @create 2023/3/22 12:37
 */
@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
