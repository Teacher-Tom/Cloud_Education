package xyz.likailing.cloud.service.msg.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.likailing.cloud.service.msg.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-03-25 14:18:59
* @Entity xyz.likailing.cloud.service.msg.entity.Message
*/
public interface MessageMapper extends BaseMapper<Message> {
    List<Message> selectAllByCourseIdOrderByGmtCreateDesc(@Param("courseId") String courseId);
}




