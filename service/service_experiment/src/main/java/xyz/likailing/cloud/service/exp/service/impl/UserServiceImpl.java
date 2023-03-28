package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.User;
import xyz.likailing.cloud.service.exp.service.UserService;
import xyz.likailing.cloud.service.exp.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-28 16:05:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




