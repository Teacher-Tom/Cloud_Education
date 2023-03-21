package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.User;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.service.CourseService;
import xyz.likailing.cloud.service.manager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/ucenter/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @ApiOperation("根据用户id获取该用户的课程列表")
    @GetMapping("/user-course")
    public R getUserCourse(String id) {
        User user = userService.getById(id);
        String role = user.getRole();
        String roleId = user.getRoleId();
        if(!ObjectUtils.isEmpty(roleId)) {
            CourseQueryVO courseQueryVO = new CourseQueryVO(roleId, null, null);
            if("student".equals(role)) {
                List<CourseVO> courses = courseService.listYearTermStudent(courseQueryVO);
                return R.ok().data("courses", courses);
            }
            else if("teacher".equals(role)) {
                List<CourseVO> courses = courseService.listYearTermTeacher(courseQueryVO);
                return R.ok().data("courses", courses);
            }
        }
        return R.error().message("用户角色错误");
    }
}
