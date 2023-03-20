package xyz.likailing.cloud.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.entity.Role;
import xyz.likailing.cloud.service.service.RoleService;
import xyz.likailing.cloud.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【role】的数据库操作Service实现
* @createDate 2023-03-19 11:57:21
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




