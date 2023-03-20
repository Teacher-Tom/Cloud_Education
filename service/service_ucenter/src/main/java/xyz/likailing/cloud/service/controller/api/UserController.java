package xyz.likailing.cloud.service.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.entity.User;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.service.CourseService;
import xyz.likailing.cloud.service.manager.service.StudentService;
import xyz.likailing.cloud.service.manager.service.TeacherService;
import xyz.likailing.cloud.service.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/17 22:46
 */
@Api(description = "用户管理")
@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        userService.register(registerVo);
        return R.ok().message("注册成功");
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = userService.login(loginVo);
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request){
        try{
            JwtInfo jwtInfo = JwtUtils.getUserIdByJwtToken(request);
            return R.ok().data("userInfo",jwtInfo);
        }catch (Exception e){
            log.error("解析用户信息失败"+e.getMessage());
            throw new CloudException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation("根据用户id获取该用户的课程列表")
    public R getUserCourse(String id) {
        User user = userService.getById(id);
        String role = user.getRole();
        String roleId = user.getRoleId();
        CourseQueryVO courseQueryVO = new CourseQueryVO(roleId, null, null);
        if("student".equals(role)) {
            List<CourseVO> courses = courseService.listYearTermStudent(courseQueryVO);
            return R.ok().data("courses", courses);
        }
        else if("teacher".equals(role)) {
            List<CourseVO> courses = courseService.listYearTermTeacher(courseQueryVO);
            return R.ok().data("courses", courses);
        }
        return R.error().message("用户角色错误");
    }

    @RequestMapping("test")
    @PreAuthorize("hasAuthority('auth_test')")
    public R testController(){
        return R.ok().message("测试成功");
    }
    @RequestMapping("logout")
    public R logout(){
        userService.logout();
        return R.ok().message("注销成功");
    }
}
