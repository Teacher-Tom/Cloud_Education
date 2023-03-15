package xyz.likailing.cloud.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.likailing.cloud.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.edu.entity.form.CourseInfoForm;
import xyz.likailing.cloud.service.edu.entity.vo.CourseQueryVo;
import xyz.likailing.cloud.service.edu.entity.vo.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);
}
