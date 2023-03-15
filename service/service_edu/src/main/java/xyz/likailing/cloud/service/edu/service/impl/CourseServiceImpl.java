package xyz.likailing.cloud.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.service.edu.entity.Course;
import xyz.likailing.cloud.service.edu.entity.CourseDescription;
import xyz.likailing.cloud.service.edu.entity.form.CourseInfoForm;
import xyz.likailing.cloud.service.edu.entity.vo.CourseQueryVo;
import xyz.likailing.cloud.service.edu.entity.vo.CourseVo;
import xyz.likailing.cloud.service.edu.mapper.CourseDescriptionMapper;
import xyz.likailing.cloud.service.edu.mapper.CourseMapper;
import xyz.likailing.cloud.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        //自动回填id
        baseMapper.insert(course);
        //保存courseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);
        return course.getId();

    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //根据id获取course
        Course course = baseMapper.selectById(id);
        if (course == null){
            return null;
        }
        //根据id获取courseDescription
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //组装
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        //自动回填id
        baseMapper.updateById(course);
        //保存courseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");


        String title = courseQueryVo.getTitle();
        String teacherId = courseQueryVo.getTeacherId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("c.titel",title);
        }
        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.like("c.teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.like("c.subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.like("c.subject_id",subjectId);
        }

        Page<CourseVo> pageParam = new Page<>(page, limit);

        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo(pageParam);

        return pageParam.setRecords(records);
    }
}
