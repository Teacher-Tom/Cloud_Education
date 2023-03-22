package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.manager.entity.User;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.mapper.UserMapper;
import xyz.likailing.cloud.service.manager.service.CourseService;
import xyz.likailing.cloud.service.manager.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/manager/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserMapper userMapper;
    @ApiOperation("根据用户id获取该用户的课程列表")
    @GetMapping("/user-course")
    public R getUserCourse(String id) {
        User user = userService.getById(id);
        if(Objects.isNull(user)){
            throw new CloudException("没有找到该id",20001);
        }
        String role = user.getRole();
        String roleId = userMapper.getRoleIdByUserId(user.getId());
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
        }else {
            throw new CloudException("没有找到该用户角色id",20001);
        }
        return R.error().message("用户角色错误");
    }
}
