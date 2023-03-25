package xyz.likailing.cloud.service.msg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.msg.entity.User;
import xyz.likailing.cloud.service.msg.service.UserService;
import xyz.likailing.cloud.service.msg.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-25 14:35:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




