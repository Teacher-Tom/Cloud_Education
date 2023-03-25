package xyz.likailing.cloud.service.msg.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.likailing.cloud.service.msg.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12042
* @description 针对表【message】的数据库操作Service
* @createDate 2023-03-25 14:18:59
*/
public interface MessageService extends IService<Message> {
    void sendMessage(String courseId,
                     String teacherId,
                     String title,
                     String content);

    Message checkMessage(String msgId);

    List<Message> listMessageByUserId(String userId);
}
