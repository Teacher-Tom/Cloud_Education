package xyz.likailing.cloud.service.manager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import xyz.likailing.cloud.service.manager.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface CourseService extends IService<Course> {

    List<CourseVO> listYearTermStudent(CourseQueryVO CourseQueryVO);

    List<CourseVO> listYearTermTeacher(CourseQueryVO CourseQueryVO);

    List<CourseVO> listAll();

    IPage<CourseVO> listPage(Long page, Long limit, AdminCourseQueryVO adminCourseQueryVO);

    boolean saveCourse(Course course, List<String> teacherIds, List<String> classIds);

    boolean removeCourseById(String id);

    CourseVO getCourseById(String id);
}
