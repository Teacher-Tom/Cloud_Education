package xyz.likailing.cloud.service.manager.service.impl;

import xyz.likailing.cloud.service.manager.entity.Message;
import xyz.likailing.cloud.service.manager.mapper.MessageMapper;
import xyz.likailing.cloud.service.manager.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
