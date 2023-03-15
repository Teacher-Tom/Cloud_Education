package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.manager.entity.Teacher;
import xyz.likailing.cloud.service.manager.entity.TeacherCourse;
import xyz.likailing.cloud.service.manager.mapper.TeacherCourseMapper;
import xyz.likailing.cloud.service.manager.mapper.TeacherMapper;
import xyz.likailing.cloud.service.manager.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public boolean removeTeacherById(String id) {
        int delete = baseMapper.deleteById(id);

        QueryWrapper<TeacherCourse> teacherCourseQueryWrapper = new QueryWrapper<>();
        teacherCourseQueryWrapper.eq("teacher_id", id);
        teacherCourseMapper.delete(teacherCourseQueryWrapper);

        return (delete >= 1);
    }
}
