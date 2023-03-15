package xyz.likailing.cloud.service.edu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.edu.feign.fallback.OssFileServiceFallBack;

/**
 * @Author 12042
 * @create 2023/3/6 22:52
 */
@FeignClient(value = "service-oss",fallback = OssFileServiceFallBack.class)
public interface OssFileService {
    @GetMapping("/admin/oss/file/test")
    public R test();
    @DeleteMapping("/admin/oss/file/remove")
    public R removeFile(@RequestParam String url);
}
