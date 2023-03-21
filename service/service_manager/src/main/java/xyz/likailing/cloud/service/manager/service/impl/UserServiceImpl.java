package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.likailing.cloud.service.manager.entity.User;
import xyz.likailing.cloud.service.manager.mapper.UserMapper;
import xyz.likailing.cloud.service.manager.service.UserService;


/**
* @author 12042
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-17 22:43:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




