package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkStudent;
import xyz.likailing.cloud.service.manager.entity.vo.StudentHomeworkVO;
import xyz.likailing.cloud.service.manager.entity.vo.TeacherHomeworkVO;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkContextMapper;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkMapper;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkStudentMapper;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private CourseHomeworkStudentMapper homeworkStudentMapper;

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
    public List<TeacherHomeworkVO> listTeacherHomework(String teacherId) {
        return baseMapper.selectByTeacherId(teacherId);
    }

    @Override
    public List<StudentHomeworkVO> listStudentHomework(String studentId) {
        List<StudentHomeworkVO> studentHomeworks = baseMapper.selectByStudentId(studentId);
        for (StudentHomeworkVO homework : studentHomeworks) {
            String homeworkId = homework.getHomeworkId();
            getCorrecting(studentId, homeworkId, homework);
        }
        return studentHomeworks;
    }

    @Override
    public StudentHomeworkVO getStudentHomework(String studentId, String id) {
        StudentHomeworkVO homework = baseMapper.selectStudentHomework(studentId, id);
        getCorrecting(studentId, id, homework);
        return homework;
    }

    @Override
    public List<CourseHomework> listExpiredHomework() {
        return baseMapper.selectExpiredHomework(new Date());
    }

    @Override
    public CourseHomework getByNodeId(String nodeId) {
        QueryWrapper<CourseHomework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node_id",nodeId);
        CourseHomework homework = baseMapper.selectOne(queryWrapper);
        return homework;
    }

    /**
     * 为作业对象补充批改信息
     * @param studentId 学生id
     * @param id 作业id
     * @param homework 作业对象
     */
    public void getCorrecting(String studentId, String id, StudentHomeworkVO homework) {
        QueryWrapper<CourseHomeworkStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("homework_id", id).eq("student_id", studentId);
        CourseHomeworkStudent courseHomeworkStudent = homeworkStudentMapper.selectOne(wrapper);
        if(!ObjectUtils.isEmpty(courseHomeworkStudent)) {
            homework.setMarked(true);
            homework.setScore(courseHomeworkStudent.getScore());
            homework.setRemark(courseHomeworkStudent.getRemark());
        }
        else {
            homework.setMarked(false);
            homework.setScore(0);
        }
    }

}
