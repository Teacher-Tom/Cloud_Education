package xyz.likailing.cloud.service.manager.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.StudentHomeworkVO;
import xyz.likailing.cloud.service.manager.entity.vo.TeacherHomeworkVO;

import java.util.Date;
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

    List<TeacherHomeworkVO> selectByTeacherId(String teacherId);

    List<StudentHomeworkVO> selectByStudentId(String studentId);

    StudentHomeworkVO selectStudentHomework(String studentId, String id);

    List<CourseHomework> selectExpiredHomework(Date date);
}
