package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkSubmit;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkSubmitMapper;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkSubmitService;

import java.util.List;

@Service
public class CourseHomeworkSubmitServiceImpl extends ServiceImpl<CourseHomeworkSubmitMapper, CourseHomeworkSubmit> implements CourseHomeworkSubmitService {

    @Override
    public boolean saveBatchSubmits(String homeworkId, String studentId, List<CourseHomeworkSubmit> submits) {
        boolean save = true;
        for (CourseHomeworkSubmit submit : submits) {
            //检查是否已经提交过
            QueryWrapper<CourseHomeworkSubmit> wrapper = new QueryWrapper<>();
            wrapper.eq("homework_id", homeworkId)
                    .eq("student_id", studentId)
                    .eq("context_id", submit.getContextId());
            CourseHomeworkSubmit one = baseMapper.selectOne(wrapper);
            if(!ObjectUtils.isEmpty(one)) {
                //已经提交过的题目只更新内容
                one.setSubmitAnswer(submit.getSubmitAnswer());
                int update = baseMapper.updateById(one);
                save = save & (update > 0);
            } else {
                submit.setStudentId(studentId);
                submit.setHomeworkId(homeworkId);
                int insert = baseMapper.insert(submit);
                save = save & (insert > 0);
            }
        }
        return save;
    }
}
