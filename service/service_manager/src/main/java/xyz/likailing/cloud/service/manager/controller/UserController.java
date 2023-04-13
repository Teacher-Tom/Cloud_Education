package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.entity.Teacher;
import xyz.likailing.cloud.service.manager.entity.User;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.mapper.UserMapper;
import xyz.likailing.cloud.service.manager.service.CourseService;
import xyz.likailing.cloud.service.manager.service.StudentService;
import xyz.likailing.cloud.service.manager.service.TeacherService;
import xyz.likailing.cloud.service.manager.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/manager/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("根据用户id获取该用户的课程列表")
    @GetMapping("/user-course/{id}")
    public R getUserCourse(@PathVariable String id) {
        User user = userService.getById(id);
        if(Objects.isNull(user)){
            throw new CloudException("没有找到该id",20001);
        }
        String role = userMapper.getRoleByUserId(user.getId());
        if(!ObjectUtils.isEmpty(role)) {
            CourseQueryVO courseQueryVO;
            if("student".equals(role)) {
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.eq("user_id", id);
                Student student = studentService.getOne(studentQueryWrapper);

                courseQueryVO = new CourseQueryVO(student.getId(), null, null);
                List<CourseVO> courses = courseService.listYearTermStudent(courseQueryVO);
                return R.ok().data("courses", courses);
            }
            else if("teacher".equals(role)) {
                QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
                teacherQueryWrapper.eq("user_id", id);
                Teacher teacher = teacherService.getOne(teacherQueryWrapper);

                courseQueryVO = new CourseQueryVO(teacher.getId(), null, null);
                List<CourseVO> courses = courseService.listYearTermTeacher(courseQueryVO);
                return R.ok().data("courses", courses);
            }
        }else {
            throw new CloudException("没有找到该用户角色id",20001);
        }
        return R.error().message("用户角色错误");
    }
}
