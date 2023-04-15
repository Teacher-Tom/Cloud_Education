package xyz.likailing.cloud.service.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.entity.User;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;
import xyz.likailing.cloud.service.feign.AllsService;
import xyz.likailing.cloud.service.service.UserService;

import java.util.Map;

/**
 * @Author 12042
 * @create 2023/3/17 22:46
 */
@Api(description = "用户管理")
@CrossOrigin
@RestController
@RequestMapping("/ucenter/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AllsService allsService;

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        User user = userService.register(registerVo);
        allsService.createAccount(user.getId(),registerVo.getUsername(),registerVo.getPassword(),registerVo.getNickname());
        return R.ok().message("注册成功").data("user",user);
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = userService.login(loginVo);
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @PostMapping("get-login-info")
    public R getLoginInfo(@RequestBody Map<String,String> map){
        try{
            String token = map.get("token");
            if(StringUtils.isEmpty(token)){
                throw new CloudException("没有token",20001);
            }
            JwtInfo jwtInfo = JwtUtils.getUserIdByJwtToken(token);
            return R.ok().data("userInfo",jwtInfo);
        }catch (Exception e){
            log.error("解析用户信息失败"+e.getMessage());
            throw new CloudException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }




    @GetMapping("test")
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
