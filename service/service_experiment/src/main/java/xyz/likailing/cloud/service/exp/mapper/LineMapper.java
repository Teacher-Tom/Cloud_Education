package xyz.likailing.cloud.service.exp.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.likailing.cloud.service.exp.entity.Line;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【exp_line】的数据库操作Mapper
* @createDate 2023-03-28 17:51:25
* @Entity xyz.likailing.cloud.service.exp.entity.Line
*/
public interface LineMapper extends BaseMapper<Line> {
    List<Line> getAllByFromNodeIdOrderByToNodeId(@Param("fromNodeId") String fromNodeId);
}




