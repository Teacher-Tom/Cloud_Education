package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;
import xyz.likailing.cloud.service.manager.entity.vo.HomeworkVO;
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
    public String saveHomework(HomeworkVO homeworkVO) {
        //存储作业基本信息
        CourseHomework homework = new CourseHomework();
        BeanUtils.copyProperties(homeworkVO, homework);
        int insertHomework = baseMapper.insert(homework);
        if(insertHomework <= 0) return null;
        //存储作业内容
        String id = homework.getId();
        List<CourseHomeworkContext> contexts = homeworkVO.getContexts();
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

    @Override
    public HomeworkVO getHomework(String id) {
        HomeworkVO homeworkVO = new HomeworkVO();
        CourseHomework homework = baseMapper.selectById(id);
        if(!ObjectUtils.isEmpty(homework)) {
            //作业基本信息
            BeanUtils.copyProperties(homework, homeworkVO);
            //作业详细内容
            QueryWrapper<CourseHomeworkContext> wrapper = new QueryWrapper<>();
            wrapper.eq("homework_id", id);
            List<CourseHomeworkContext> contexts = contextMapper.selectList(wrapper);
            homeworkVO.setContexts(contexts);
            return homeworkVO;
        }
        return null;
    }
}
