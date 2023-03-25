package xyz.likailing.cloud.service.msg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.msg.entity.ManagerStudent;
import xyz.likailing.cloud.service.msg.service.ManagerStudentService;
import xyz.likailing.cloud.service.msg.mapper.ManagerStudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【manager_student】的数据库操作Service实现
* @createDate 2023-03-25 14:55:49
*/
@Service
public class ManagerStudentServiceImpl extends ServiceImpl<ManagerStudentMapper, ManagerStudent>
    implements ManagerStudentService{

}




