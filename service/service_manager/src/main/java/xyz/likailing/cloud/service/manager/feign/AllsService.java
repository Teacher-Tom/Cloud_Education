package xyz.likailing.cloud.service.manager.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author 12042
 * @create 2023/3/23 16:19
 */
@FeignClient(value = "service-alls")
public interface AllsService {

}
