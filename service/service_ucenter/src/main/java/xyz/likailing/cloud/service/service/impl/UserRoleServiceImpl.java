package xyz.likailing.cloud.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.entity.UserRole;
import xyz.likailing.cloud.service.service.UserRoleService;
import xyz.likailing.cloud.service.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【user_role】的数据库操作Service实现
* @createDate 2023-03-19 15:54:20
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




