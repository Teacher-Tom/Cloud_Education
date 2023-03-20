package xyz.likailing.cloud.service.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        CourseHomeworkContext homeworkContext = new CourseHomeworkContext();
        BeanUtils.copyProperties(homeworkVO, homeworkContext);
        homeworkContext.setHomeworkId(id); //关联基本信息
        int insertContext = contextMapper.insert(homeworkContext);
        if(insertContext > 0) return id;
        else {
            baseMapper.deleteById(id);
            return null;
        }
    }

    @Override
    public List<CourseHomework> listTeacherHomework(String teacherId) {
        return baseMapper.selectTeacherHomework(teacherId);
    }
}
