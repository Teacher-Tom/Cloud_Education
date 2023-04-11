package xyz.likailing.cloud.service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.entity.UcenterMember;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;
import xyz.likailing.cloud.service.service.UcenterMemberService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token= memberService.login(member);
        UcenterMember mem=memberService.login1(member);
        //System.out.println(mem);
        return R.ok().data("token", token).data("mem",mem);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //查询用户信息
    @ApiOperation(value = "根据用户表id查询用户信息")
    @GetMapping("getMemberInfo/{id}")
    public R getMemberInfo(@PathVariable String id){
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        UcenterMember ucenterMember = memberService.getOne(wrapper);
        return R.ok().data("member",ucenterMember);
    }

    //修改用户信息
    @ApiOperation(value = "更新用户信息")
    @PostMapping("updateMemberInfo")
    public R updateMemberInfo(@RequestBody UcenterMember ucenterMember){
        String id = ucenterMember.getId();
        QueryWrapper<UcenterMember> w=new QueryWrapper<>();
        w.eq("id",id);
        UcenterMember one = memberService.getOne(w);
        UcenterMember member=new UcenterMember();
        member.setId(ucenterMember.getId());
        member.setNeicun(one.getNeicun());
        member.setAvatar(ucenterMember.getAvatar());
        member.setNickname(ucenterMember.getNickname());
        boolean b = memberService.updateById(member);
        if (b){
            return R.ok();
        }else{
            return R.error();
        }
    }
    @ApiOperation("创建课程网盘")
    @PostMapping("create-course")
    public R createCourseAccount(@RequestParam String courseId,@RequestParam String courseName,@RequestParam(required = false) String coverUrl){
        if (StringUtils.isEmpty(coverUrl)){
            // 默认封面
            coverUrl = "https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/default.jpg";
        }
        memberService.createCourse(courseId,courseName,coverUrl);
        return R.ok().message("创建课程网盘成功");
    }

}

