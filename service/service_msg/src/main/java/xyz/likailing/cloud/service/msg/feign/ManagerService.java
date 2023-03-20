package xyz.likailing.cloud.service.msg.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author 12042
 * @create 2023/3/20 20:14
 */
@FeignClient(value = "service-manager")
public interface ManagerService {

}
