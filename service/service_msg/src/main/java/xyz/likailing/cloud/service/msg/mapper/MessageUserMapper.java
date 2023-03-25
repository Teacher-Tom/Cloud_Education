package xyz.likailing.cloud.service.msg.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.likailing.cloud.service.msg.entity.Message;
import xyz.likailing.cloud.service.msg.entity.MessageUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12042
* @description 针对表【message_user】的数据库操作Mapper
* @createDate 2023-03-25 14:18:59
* @Entity xyz.likailing.cloud.service.msg.entity.MessageUser
*/
public interface MessageUserMapper extends BaseMapper<MessageUser> {
    List<Message> selectAllMessagesByUserId(@Param("userId") String userId);

    List<Boolean> selectHasReadByUserId(@Param("userId") String userId);
}




