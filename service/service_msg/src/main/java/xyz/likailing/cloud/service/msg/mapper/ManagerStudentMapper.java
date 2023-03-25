package xyz.likailing.cloud.service.msg.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.likailing.cloud.service.msg.entity.ManagerStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【manager_student】的数据库操作Mapper
* @createDate 2023-03-25 14:55:49
* @Entity xyz.likailing.cloud.service.msg.entity.ManagerStudent
*/
public interface ManagerStudentMapper extends BaseMapper<ManagerStudent> {
    List<String> selectUserIdByClassId(@Param("classId") String classId);
}




