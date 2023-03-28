package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Task;
import xyz.likailing.cloud.service.exp.service.TaskService;
import xyz.likailing.cloud.service.exp.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【exp_task】的数据库操作Service实现
* @createDate 2023-03-28 15:09:48
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




