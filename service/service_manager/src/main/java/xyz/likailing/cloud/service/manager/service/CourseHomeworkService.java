package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
public interface CourseHomeworkService extends IService<CourseHomework> {

    String saveHomework(CourseHomework homework, List<CourseHomeworkContext> contexts);

    List<CourseHomework> listTeacherHomework(String teacherId);
}
