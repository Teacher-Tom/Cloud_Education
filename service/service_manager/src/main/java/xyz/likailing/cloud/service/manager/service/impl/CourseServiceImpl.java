package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.service.manager.entity.*;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseVO;
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
        return baseMapper.selectYTStuCourses(courseQueryVO);
    }

    @Override
    public List<CourseVO> listYearTermTeacher(CourseQueryVO courseQueryVO) {
        return baseMapper.selectYTTeaCourses(courseQueryVO);
    }

    @Override
    public List<AdminCourseVO> listAll() {
        return baseMapper.selectAllCourses();
    }

    @Override
    public IPage<AdminCourseVO> listPage(Long page, Long limit, AdminCourseQueryVO adminCourseQueryVO) {
        Page<AdminCourseVO> adminCourseVOPage = new Page<>(page, limit);
        QueryWrapper<AdminCourseVO> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("c.id");
        if(!ObjectUtils.isEmpty(adminCourseQueryVO)) {
            String courseId = adminCourseQueryVO.getCourseId();
            String courseName = adminCourseQueryVO.getCourseName();
            String department = adminCourseQueryVO.getDepartment();
            Integer year = adminCourseQueryVO.getYear();
            Integer term = adminCourseQueryVO.getTerm();
            if(!ObjectUtils.isEmpty(courseId)) {
                wrapper.eq("c.id", courseId);
            }
            if(!ObjectUtils.isEmpty(courseName)) {
                wrapper.like("c.name", courseName);
            }
            if(!ObjectUtils.isEmpty(department)) {
                wrapper.eq("c.department", department);
            }
            if(!ObjectUtils.isEmpty(year) && year > 0) {
                wrapper.eq("c.year", year);
            }
            if(!ObjectUtils.isEmpty(term) && term > 0) {
                wrapper.eq("c.term", term);
            }
        }
        List<AdminCourseVO> courses = baseMapper.selectPageCourses(adminCourseVOPage, wrapper);
        adminCourseVOPage.setRecords(courses);
        return adminCourseVOPage;
    }

    @Override
    public boolean saveCourse(Course course, String teacherId, String classId) {
        int insert = baseMapper.insert(course);

        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCourseId(course.getId());
        teacherCourse.setTeacherId(teacherId);
        teacherCourseMapper.insert(teacherCourse);

        ClassCourse classCourse = new ClassCourse();
        classCourse.setCourseId(course.getId());
        classCourse.setClassId(classId);
        classCourseMapper.insert(classCourse);

        return (insert > 0);
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