package xyz.likailing.cloud.service.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 12042
 * @create 2023/3/20 19:09
 */
@SpringBootApplication
@ComponentScan("xyz.likailing.cloud")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceMsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsgApplication.class,args);
    }
}
