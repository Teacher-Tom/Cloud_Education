package xyz.likailing.cloud.service.edu.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.edu.feign.OssFileService;

/**
 * @Author 12042
 * @create 2023/3/7 23:14
 */
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }
}
