package xyz.likailing.cloud.service.manager.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import xyz.likailing.cloud.service.manager.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likailing
 * @since 2023-03-11
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    /* 根据学生id和学年学期查询课程列表 */
    List<CourseVO> selectYTStuCourses(@Param(Constants.WRAPPER) QueryWrapper<CourseVO> wrapper);

    /* 根据教师id和学年学期查询课程列表 */
    List<CourseVO> selectYTTeaCourses(@Param(Constants.WRAPPER) QueryWrapper<CourseVO> wrapper);

    /* 列出全部课程的详细信息 */
    List<CourseVO> selectAllCourses();

    /* 分页查询课程 */
    List<CourseVO> selectPageCourses(Page<CourseVO> adminCourseVOPage, @Param(Constants.WRAPPER) QueryWrapper<CourseVO> wrapper);

    CourseVO selectCourseById(String id);
}
