package xyz.likailing.cloud.service.manager.mapper;

import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
public interface CourseHomeworkMapper extends BaseMapper<CourseHomework> {

    List<CourseHomework> selectTeacherHomework(String teacherId);
}
