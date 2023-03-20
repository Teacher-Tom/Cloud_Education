package xyz.likailing.cloud.service.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkContextMapper;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkMapper;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@Service
public class CourseHomeworkServiceImpl extends ServiceImpl<CourseHomeworkMapper, CourseHomework> implements CourseHomeworkService {

    @Autowired
    private CourseHomeworkContextMapper contextMapper;

    @Override
    public String saveHomework(CourseHomework homework, List<CourseHomeworkContext> contexts) {
        //存储作业基本信息
        int insertHomework = baseMapper.insert(homework);
        if(insertHomework <= 0) return null;
        //存储作业内容
        String id = homework.getId();
        int insertContext = 0;
        for (CourseHomeworkContext context : contexts) {
            context.setHomeworkId(id);
            insertContext += contextMapper.insert(context);
        }
        if(insertContext > 0) return id;
        return null;
    }

    @Override
    public List<CourseHomework> listTeacherHomework(String teacherId) {
        return baseMapper.selectTeacherHomework(teacherId);
    }

}
