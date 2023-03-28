package xyz.likailing.cloud.service.exp.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.likailing.cloud.service.exp.entity.Experiment;
import xyz.likailing.cloud.service.exp.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12042
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-03-28 16:05:46
* @Entity xyz.likailing.cloud.service.exp.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    List<Experiment> selectExpsByStudentId(@Param("userId") String id);

    List<Experiment> selectExpsByTeacherId(@Param("userId") String id);
}




