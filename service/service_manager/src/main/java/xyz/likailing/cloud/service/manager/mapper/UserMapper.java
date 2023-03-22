package xyz.likailing.cloud.service.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.likailing.cloud.service.manager.entity.User;

/**
* @author 12042
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-17 22:43:33
* @Entity xyz.likailing.cloud.service.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




