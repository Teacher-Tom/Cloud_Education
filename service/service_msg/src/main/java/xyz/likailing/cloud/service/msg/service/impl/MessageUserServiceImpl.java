package xyz.likailing.cloud.service.msg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.msg.entity.MessageUser;
import xyz.likailing.cloud.service.msg.service.MessageUserService;
import xyz.likailing.cloud.service.msg.mapper.MessageUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【message_user】的数据库操作Service实现
* @createDate 2023-03-25 14:18:59
*/
@Service
public class MessageUserServiceImpl extends ServiceImpl<MessageUserMapper, MessageUser>
    implements MessageUserService{

}




