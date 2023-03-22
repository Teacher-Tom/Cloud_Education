package xyz.likailing.cloud.service.manager.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.likailing.cloud.service.manager.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-22 09:37:46
* @Entity xyz.likailing.cloud.service.manager.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    String getRoleIdByUserId(@Param("id") String id);



}




