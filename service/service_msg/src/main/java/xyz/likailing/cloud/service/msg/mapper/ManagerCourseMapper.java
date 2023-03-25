package xyz.likailing.cloud.service.msg.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.likailing.cloud.service.msg.entity.ManagerCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12042
* @description 针对表【manager_course】的数据库操作Mapper
* @createDate 2023-03-25 15:08:14
* @Entity xyz.likailing.cloud.service.msg.entity.ManagerCourse
*/
public interface ManagerCourseMapper extends BaseMapper<ManagerCourse> {
    List<String> selectClassIdByCourseId(@Param("courseId") String courseId);
}




