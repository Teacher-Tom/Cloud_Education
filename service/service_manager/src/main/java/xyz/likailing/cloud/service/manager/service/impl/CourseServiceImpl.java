package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.service.manager.entity.*;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.mapper.ClassCourseMapper;
import xyz.likailing.cloud.service.manager.mapper.CourseMapper;
import xyz.likailing.cloud.service.manager.mapper.TeacherCourseMapper;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TimetableMapper timetableMapper;
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;
    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Override
    public List<CourseVO> listYearTermStudent(CourseQueryVO courseQueryVO) {
        QueryWrapper<CourseVO> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("mc.id");
        if(!ObjectUtils.isEmpty(courseQueryVO)) {
            String id = courseQueryVO.getId();
            Integer year = courseQueryVO.getYear();
            Integer term = courseQueryVO.getTerm();
            if(!ObjectUtils.isEmpty(id)) {
                wrapper.eq("s.id", id);
            }
            if(!ObjectUtils.isEmpty(year) && year > 0) {
                wrapper.eq("mc.year", year);
            }
            if(!ObjectUtils.isEmpty(term) && term > 0) {
                wrapper.eq("mc.term", term);
            }
        }
        return baseMapper.selectYTStuCourses(wrapper);
    }

    @Override
    public List<CourseVO> listYearTermTeacher(CourseQueryVO courseQueryVO) {
        QueryWrapper<CourseVO> wrapper = new QueryWrapper<>();
//        wrapper.orderByAsc("mc.id");
        if(!ObjectUtils.isEmpty(courseQueryVO)) {
            String id = courseQueryVO.getId();
            Integer year = courseQueryVO.getYear();
            Integer term = courseQueryVO.getTerm();
            if(!ObjectUtils.isEmpty(id)) {
                wrapper.eq("t.id", id);
            }
            if(!ObjectUtils.isEmpty(year) && year > 0) {
                wrapper.eq("mc.year", year);
            }
            if(!ObjectUtils.isEmpty(term) && term > 0) {
                wrapper.eq("mc.term", term);
            }
        }
        return baseMapper.selectYTTeaCourses(wrapper);
    }

    @Override
    public List<CourseVO> listAll() {
        return baseMapper.selectAllCourses();
    }

    @Override
    public IPage<CourseVO> listPage(Long page, Long limit, AdminCourseQueryVO adminCourseQueryVO) {
        Page<CourseVO> courseVOPage = new Page<>(page, limit);
        QueryWrapper<CourseVO> wrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(adminCourseQueryVO)) {
            String courseId = adminCourseQueryVO.getCourseId();
            String courseName = adminCourseQueryVO.getCourseName();
            String department = adminCourseQueryVO.getDepartment();
            Integer year = adminCourseQueryVO.getYear();
            Integer term = adminCourseQueryVO.getTerm();
            if(!ObjectUtils.isEmpty(courseId)) {
                wrapper.eq("mc.id", courseId);
            }
            if(!ObjectUtils.isEmpty(courseName)) {
                wrapper.like("mc.name", courseName);
            }
            if(!ObjectUtils.isEmpty(department)) {
                wrapper.eq("mc.department", department);
            }
            if(!ObjectUtils.isEmpty(year) && year > 0) {
                wrapper.eq("mc.year", year);
            }
            if(!ObjectUtils.isEmpty(term) && term > 0) {
                wrapper.eq("mc.term", term);
            }
        }
        List<CourseVO> courses = baseMapper.selectPageCourses(courseVOPage, wrapper);
        courseVOPage.setRecords(courses);
        return courseVOPage;
    }

    @Override
    public boolean saveCourse(Course course, List<String> teacherIds, List<String> classIds) {
        int insert = baseMapper.insert(course);
        if(insert <= 0) return false;
        String courseId = course.getId();

        for (String teacherId : teacherIds) {
            TeacherCourse teacherCourse = new TeacherCourse();
            teacherCourse.setCourseId(courseId);
            teacherCourse.setTeacherId(teacherId);
            teacherCourseMapper.insert(teacherCourse);
        }

        for (String classId : classIds) {
            ClassCourse classCourse = new ClassCourse();
            classCourse.setCourseId(courseId);
            classCourse.setClassId(classId);
            classCourseMapper.insert(classCourse);
        }

        return true;
    }

    @Override
    public boolean removeCourseById(String id) {
        int delete = baseMapper.deleteById(id);

        QueryWrapper<Timetable> timetableQueryWrapper = new QueryWrapper<>();
        timetableQueryWrapper.eq("course_id", id);
        timetableMapper.delete(timetableQueryWrapper);

        QueryWrapper<TeacherCourse> teacherCourseQueryWrapper = new QueryWrapper<>();
        teacherCourseQueryWrapper.eq("course_id", id);
        teacherCourseMapper.delete(teacherCourseQueryWrapper);

        QueryWrapper<ClassCourse> classCourseQueryWrapper = new QueryWrapper<>();
        classCourseQueryWrapper.eq("course_id", id);
        classCourseMapper.delete(classCourseQueryWrapper);

        return (delete >= 1);
    }
}
