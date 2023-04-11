package xyz.likailing.cloud.service.manager.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.vo.MessageVo;

/**
 * @Author 12042
 * @create 2023/4/11 22:26
 */
@FeignClient(value = "service-msg")
@Component
public interface MsgService {
    @PostMapping("/msg/send")
    public R sendMessage(@RequestBody MessageVo msgVo);
}
